const { UserModel } = require("../../models/userModel");
const { EvaluationResult } = require("../utils/EvaluationResult");

exports.UsernameValidator = class UsernameValidator {
    /**
     * 
     * @param {String} username 
     */
    constructor(username) {
        this.username = username;
    }

    /**
     * 
     * @private
     * @returns {Promise<boolean>}
     */
    async isUsernameAvailable() {
        const records = await UserModel.find({ username: this.username });
        return records.length == 0;
    }

    /**
     * @public
     * @returns {Promise<EvaluationResult>}
     */
    async evaluate() {
        if (!(await this.isUsernameAvailable())) {
            return new EvaluationResult(false, "This username is not available.");
        }

        return new EvaluationResult(true);
    }

}