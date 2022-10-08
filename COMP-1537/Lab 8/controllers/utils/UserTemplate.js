const { UserModel } = require("../../models/userModel");
const bcrypt = require("bcrypt");

exports.UserTemplate = class UserTemplate {
    /**
     * 
     * @param {String} username 
     * @param {String} unencryptedPassword 
     * @param {Number | null} imageNumber 
     */
    constructor(username, unencryptedPassword, imageNumber = null) {
        this.username = username;
        this.password = unencryptedPassword;
        this.imageNumber = imageNumber;
    }

    /**
     * Save the user to the database, the function hashes the password before doing the operation.
     * 
     * @public
     */
    async save() {
        if (this.imageNumber == null) {
            throw new Error("Attempt to save a User with a null imageNumber")
        }

        return new UserModel({
            username: this.username,
            password: await bcrypt.hash(this.password, 10),
            imageNumber: this.imageNumber
        }).save();
    }

    /**
     * @private
     * @param {Uint8Array[]} buffer 
     */
    static processBuffer(buffer) {
        return Buffer.concat(buffer).toString();
    }

    /**
     * @public
     * @param {Uint8Array[]} buffer 
     */
    static buildFromLoginBuffer(buffer) {
        const data = this.processBuffer(buffer);
        const splitted = data.split("&");
        const usr = {}
        try {
            usr.username = splitted[0].split("=")[1];
            usr.password = splitted[1].split("=")[1];
        } catch (error) {
            console.error("The passed buffer is corrupted");
            console.error(data);
            throw error;
        }
        return new UserTemplate(usr.username, usr.password);
    }

    /**
     * @public
     * @param {Uint8Array[]} buffer 
     */
    static buildFromSignUpBuffer(buffer) {
        const data = this.processBuffer(buffer);
        const splitted = data.split("&");
        const usr = {}
        try {
            usr.username = splitted[0].split("=")[1];
            usr.password = splitted[1].split("=")[1];
            usr.imageNumber = Number(splitted[2].split("=")[1]);
        } catch (error) {
            console.error("The passed buffer is corrupted");
            console.error(data);
            throw error;
        }

        if (isNaN(usr.imageNumber)) {
            throw Error("A non number was passed to imageNumber in the buffer, " + data);
        }

        return new UserTemplate(usr.username, usr.password, usr.imageNumber);
    }
}