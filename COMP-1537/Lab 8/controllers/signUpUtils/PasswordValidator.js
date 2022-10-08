const { EvaluationResult } = require("../utils/EvaluationResult");

exports.PasswordValidator = class PasswordValidator {
    static MINIMUM_PASSWORD_LENGTH = 8;

    /**
     * 
     * @param {String} password 
     */
    constructor(password) {
        this.password = password;
    }

    /**
     * @private
     * @returns {boolean}
     */
    hasEnoughLength() {
        return this.password.length >= PasswordValidator.MINIMUM_PASSWORD_LENGTH;
    }

    /**
     * @public
     * @returns {Promise<EvaluationResult>} 
     */
    async evaluate() {
        if (!this.hasEnoughLength()) {
            return new EvaluationResult(false, `This password is too short, it should have at least ${PasswordValidator.MINIMUM_PASSWORD_LENGTH} characters.`);
        }

        return new EvaluationResult(true);
    }

}