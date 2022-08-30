/**
 * @enum {string}
 */
const ErrorMessages = {
  COMMAND_MISSING: "Please provide a command (question / answer / score).",
  PARAM_MISSING: "Missing parameter %s.",
  INVALID_QUESTION_NUMBER: "Invalid question number.",
  QUESTION_WAS_ALREADY_ANSWERED: "You already answered this question.",
  INVALID_PARAM_TYPE: "Invalid parameter %s, should be from type: %s.",
  ERROR_WHILE_READING_FILE: "Error while reading file: %s\nError: %s.",
  ERROR_WHILE_WRITING_FILE: "Error while writing file: %s\nError: %s.",
  FILE_IS_CORRUPTED:
    "Error while retreiving data from file: %s, the file is corrupted.",
};

class ErrorLogger {
  /**
   * @return {void}
   */
  commandMissing() {
    console.error(ErrorMessages.COMMAND_MISSING);
  }

  /**
   * @param {string} paramName
   * @return {void}
   */
  paramMissing(paramName) {
    console.error(ErrorMessages.PARAM_MISSING, paramName);
  }

  /**
   * @return {void}
   */
  invalidQuestionNumber() {
    console.error(ErrorMessages.INVALID_QUESTION_NUMBER);
  }

  /**
   * @return {void}
   */
  questionAlreadyAnswered() {
    console.error(ErrorMessages.QUESTION_WAS_ALREADY_ANSWERED);
  }

  /**
   * @param {string} providedParam
   * @param {string} expectedType
   * @return {void}
   */
  invalidType(providedParam, expectedType) {
    console.error(
      ErrorMessages.INVALID_PARAM_TYPE,
      providedParam,
      expectedType
    );
  }

  /**
   * @param {string} file
   * @param {Error} err
   * @return {void}
   */
  whileWritingFile(file, err) {
    console.error(ErrorMessages.ERROR_WHILE_WRITING_FILE, file, err.message);
  }

  /**
   * @param {string} file
   * @param {Error} err
   * @return {void}
   */
  whileReadingFile(file, err) {
    console.error(ErrorMessages.ERROR_WHILE_READING_FILE, file, err.message);
  }

  /**
   * @param {string} file
   * @return {void}
   */
  fileIsCorrupted(file) {
    console.error(ErrorMessages.FILE_IS_CORRUPTED, file);
  }
}

module.exports.ErrorLogger = new ErrorLogger();
