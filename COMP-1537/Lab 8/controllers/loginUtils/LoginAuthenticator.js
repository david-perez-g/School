const { UserModel } = require("../../models/userModel");
const { EvaluationResult } = require("../utils/EvaluationResult");
const { UserTemplate } = require("../utils/UserTemplate");
const bcrypt = require("bcrypt");

exports.LoginAuthenticator = class LoginAuthenticator {
    /**
     * @param {UserTemplate} user
     */
    constructor(user) {
        this.user = user;
        this.usrRecord = this.getUserRecord();
    }

    async getUserRecord() {
        return UserModel.findOne({ username: this.user.username });
    }

    async usernameExists() {
        return (await this.usrRecord) != null;
    }

    async passwordsMatch() {
        if ((await this.usrRecord) == null) {
            return false;
        }

        // @ts-ignore
        const encryptedPassword = (await this.usrRecord).password || "";
        return bcrypt.compare(this.user.password, encryptedPassword);
    }

    /**
     * @returns {Promise<EvaluationResult>}
     */
    async evaluate() {
        if (!(await this.usernameExists())) {
            return new EvaluationResult(false, `This username does not exists.`);
        }

        if (!(await this.passwordsMatch())) {
            return new EvaluationResult(false, "Wrong password.");
        }

        return new EvaluationResult(true);
    }
}