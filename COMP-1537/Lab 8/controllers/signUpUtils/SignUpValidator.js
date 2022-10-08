const { UsernameValidator } = require("./UsernameValidator");
const { PasswordValidator } = require("./PasswordValidator");
const { ImageNumberValidator } = require("./ImageNumberValidator");
const { EvaluationResult } = require("../utils/EvaluationResult");
const { UserTemplate } = require("../utils/UserTemplate");

exports.SignUpValidator = class SignUpValidator {

    /**
     * @param {UserTemplate} user
     * @returns {Promise<EvaluationResult>}
     */
    static async test(user) {
        if (user.imageNumber == null) {
            throw new Error("Received an user with a null imageNumber");
        }

        const usernameEvaluationResult = (new UsernameValidator(user.username)).evaluate();
        const passwordEvaluationResult = (new PasswordValidator(user.password)).evaluate();
        const imageNumberEvaluationResult = (new ImageNumberValidator(user.imageNumber)).evaluate();

        if (!(await usernameEvaluationResult).result) {
            return usernameEvaluationResult;
        }

        if (!(await passwordEvaluationResult).result) {
            return passwordEvaluationResult;
        }

        if (!(await imageNumberEvaluationResult).result) {
            return imageNumberEvaluationResult;
        }

        return new EvaluationResult(true);
    }
}