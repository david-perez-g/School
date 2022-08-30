/**
 * @enum {string}
 */
const Type = {
  NUMBER: "number",
  STRING: "string",
  //other types to be added in the future
};

/**
 * @enum {string}
 */
const Files = {
  ANSWERS: "./answers.json",
  QUESTIONS: "./questions.json",
  SCORE: "./score.txt",
  ANSWERED_QUESTIONS: "./answered_questions.json",
};

/**
 * @enum {string}
 */
const Commands = {
  QUESTION: "question",
  ANSWER: "answer",
  SCORE: "score",
};

/**
 * @type {number}
 */
const NUMBER_OF_QUESTIONS = 4;

/**
 * @type {number}
 */
const EXIT_FAILURE = 1;

module.exports.Type = Type;
module.exports.Files = Files;
module.exports.Commands = Commands;
module.exports.NUMBER_OF_QUESTIONS = NUMBER_OF_QUESTIONS;
module.exports.EXIT_FAILURE = EXIT_FAILURE;
