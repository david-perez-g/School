//@ts-check
const fs = require("fs");
const { exit, argv } = require("process");
const {
  Type,
  Files,
  Commands,
  NUMBER_OF_QUESTIONS,
  EXIT_FAILURE,
} = require("./constants.js");

const ErrorLogger = require("./error_logging.js").ErrorLogger;

/**Reads a file and returns a promise with the data.
 *
 * @param {string} path
 * @returns {Promise<string>}
 */
function readFileContent(path) {
  return new Promise((resolve, reject) => {
    fs.readFile(path, "utf8", (err, data) => {
      if (err) {
        reject(err);
      }

      resolve(data);
    });
  });
}

/**
 *
 * @param {string} path
 * @param {string} string
 * @returns {Promise<string>}
 */
function writeToFile(path, string) {
  return new Promise((_, reject) => {
    fs.writeFile(path, string, (err) => {
      if (err) {
        reject(err);
      }
    });
  });
}

/**A function that returns an specified cli arg after passing various checks.
 * If the param at the given index does not exist, the function will notify the user
 * and exit the program immediately.
 * If a type is provided, the function will check that the param is of the correct type
 * and if it is not, the function will, again, notify the user and exit the program.
 *
 * @param {number} index - The index of the parameter in `process.argv`.
 * @param {string} paramName - Parameter name.
 * @param {Type|null} type - Type to be checked.
 * @return {string} - The param.
 */
function getParamAtIndex(index, paramName, type = null) {
  const param = argv[index];

  if (!param) {
    ErrorLogger.paramMissing(paramName);
    exit(EXIT_FAILURE);
  }

  //checking if the param is of the specified type
  if (type && !paramIsFromType(type, param)) {
    ErrorLogger.invalidType(param, type);
    exit(EXIT_FAILURE);
  }

  return param;
}

/**
 *
 * @param {Type} type
 * @param {string} value
 * @returns {boolean}
 */
function paramIsFromType(type, value) {
  switch (type) {
    case Type.NUMBER:
      return !isNaN(Number(value));

    // other type checks

    default:
      return false;
  }
}

/**
 *
 * @param {number} number
 * @returns {boolean}
 */
function isValidQuestionNumber(number) {
  return number > 0 && number <= NUMBER_OF_QUESTIONS;
}

/**
 *
 * @param {number} questionNumber
 * @returns {void}
 */
function addAnsweredQuestion(questionNumber) {
  readFileContent(Files.ANSWERED_QUESTIONS)
    .then((data) => {
      const answeredQuestions = convertJsonStringToObject(data);

      // bad json syntax handling
      if (!answeredQuestions) {
        ErrorLogger.fileIsCorrupted(Files.ANSWERED_QUESTIONS);
        exit(EXIT_FAILURE);
      }

      try {
        answeredQuestions.push(questionNumber);
      } catch (_) {
        ErrorLogger.fileIsCorrupted(Files.ANSWERED_QUESTIONS);
        exit(EXIT_FAILURE);
      }

      writeToFile(
        Files.ANSWERED_QUESTIONS,
        JSON.stringify(answeredQuestions)
      ).catch((err) => {
        ErrorLogger.whileWritingFile(Files.ANSWERED_QUESTIONS, err);
        exit(EXIT_FAILURE);
      });
    })
    .catch((err) => {
      ErrorLogger.whileReadingFile(Files.ANSWERED_QUESTIONS, err);
      exit(EXIT_FAILURE);
    });
}

/**
 *
 * @param {number} questionNumber
 * @returns {Promise<boolean>}
 */
async function isQuestionAlreadyAnswered(questionNumber) {
  let data;
  try {
    data = await readFileContent(Files.ANSWERED_QUESTIONS);
  } catch (error) {
    ErrorLogger.whileReadingFile(Files.ANSWERED_QUESTIONS, error);
    exit(EXIT_FAILURE);
  }

  const answeredQuestions = convertJsonStringToObject(data);

  // checking if there was no syntax error while parsing
  // and assuring answeredQuestions is an array
  if (!answeredQuestions || !("includes" in answeredQuestions)) {
    ErrorLogger.fileIsCorrupted(Files.ANSWERED_QUESTIONS);
    exit(EXIT_FAILURE);
  }

  return answeredQuestions.includes(questionNumber);
}

/**
 *
 * @param {string} string - JSON string to be parsed
 * @returns {Object | null} - Returns an object in success or null in failure
 */
function convertJsonStringToObject(string) {
  try {
    return JSON.parse(string);
  } catch (_) {
    return null;
  }
}

function questionCommandHandler() {
  const questionNumber = Number(
    getParamAtIndex(3, "`question_number`", Type.NUMBER)
  );

  if (!isValidQuestionNumber(questionNumber)) {
    ErrorLogger.invalidQuestionNumber();
    exit(EXIT_FAILURE);
  }

  showQuestion(questionNumber);
}

/**
 *
 * @param {number} questionNumber
 */
function showQuestion(questionNumber) {
  readFileContent(Files.QUESTIONS)
    .then((data) => {
      const questions = convertJsonStringToObject(data);
      if (!questions) {
        ErrorLogger.fileIsCorrupted(Files.QUESTIONS);
        exit(EXIT_FAILURE);
      }

      console.log(questions[questionNumber]);
    })
    .catch((err) => {
      ErrorLogger.whileReadingFile(Files.QUESTIONS, err);
      exit(EXIT_FAILURE);
    });
}

function scoreCommandHandler() {
  readFileContent(Files.SCORE)
    .then((data) => {
      const score = parseInt(data);
      if (isNaN(score)) {
        ErrorLogger.fileIsCorrupted(Files.SCORE);
        exit(EXIT_FAILURE);
      }
      console.log(`Your score is: ${score}`);
    })
    .catch((err) => {
      ErrorLogger.whileReadingFile(Files.SCORE, err);
      exit(EXIT_FAILURE);
    });
}

async function answerCommandHandler() {
  const answer = Number(getParamAtIndex(3, "`question_answer`", Type.NUMBER));
  getParamAtIndex(4, '"question"');
  const questionNumber = Number(
    getParamAtIndex(5, "`question_number`", Type.NUMBER)
  );

  if (!isValidQuestionNumber(questionNumber)) {
    ErrorLogger.invalidQuestionNumber();
    exit(EXIT_FAILURE);
  }

  if (await isQuestionAlreadyAnswered(questionNumber)) {
    ErrorLogger.questionAlreadyAnswered();
    exit(EXIT_FAILURE);
  }

  addAnsweredQuestion(questionNumber);
  processAnswer(answer, questionNumber);
}

/**
 *
 * @param {number} answer - Answer to the question in questions.json
 * @param {number} questionNumber - question number in questions.json
 */
function processAnswer(answer, questionNumber) {
  readFileContent(Files.ANSWERS)
    .then((data) => {
      const answers = convertJsonStringToObject(data);
      if (!answers) {
        ErrorLogger.fileIsCorrupted(Files.ANSWERS);
        exit(EXIT_FAILURE);
      }

      // answer is incorrect
      if (!(answers[questionNumber - 1] === answer)) {
        return console.log("Incorrect answer :(");
      }

      console.log("Correct answer! Your score has been updated.");
      increaseScore();
    })
    .catch((err) => {
      ErrorLogger.whileReadingFile(Files.ANSWERS, err);
      exit(EXIT_FAILURE);
    });
}

/** A function that increases the current score of the user
 */
function increaseScore() {
  readFileContent(Files.SCORE)
    .then((data) => {
      const currentScore = parseInt(data);

      if (isNaN(currentScore)) {
        ErrorLogger.fileIsCorrupted(Files.SCORE);
        exit(EXIT_FAILURE);
      }

      writeToFile(Files.SCORE, `${currentScore + 1}`).catch((err) => {
        ErrorLogger.whileWritingFile(Files.SCORE, err);
        exit(EXIT_FAILURE);
      });
    })
    .catch((err) => {
      ErrorLogger.whileReadingFile(Files.SCORE, err);
    });
}

const CommandHandler = {};

CommandHandler[Commands.QUESTION] = questionCommandHandler;
CommandHandler[Commands.ANSWER] = answerCommandHandler;
CommandHandler[Commands.SCORE] = scoreCommandHandler;

/**Program starting point
 *
 * @returns {void}
 */
function main() {
  const handler = CommandHandler[argv[2]];
  if (!handler) {
    ErrorLogger.commandMissing();
    exit(EXIT_FAILURE);
  }

  handler();
}

main();
