const { UserModel } = require("../../models/userModel");
const { EvaluationResult } = require("../utils/EvaluationResult");

exports.ImageNumberValidator = class ImageNumberValidator {
    static MINIMUM_IMAGE_NUMBER = 1;
    static MAXIMUM_IMAGE_NUMBER = 99;

    /**
     * 
     * @param {Number} imageNumber 
     */
    constructor(imageNumber) {
        this.imageNumber = imageNumber;
    }

    /**
     * 
     * @returns {boolean}
     */
    isLessThanMinimum() {
        return this.imageNumber < ImageNumberValidator.MINIMUM_IMAGE_NUMBER;
    }

    /**
     * 
     * @returns {boolean}
     */
    isMoreThanMaximum() {
        return this.imageNumber > ImageNumberValidator.MAXIMUM_IMAGE_NUMBER;
    }

    /**
     * 
     * @returns {Promise<boolean>}
     */
    async isImageNumberAvailable() {
        const records = await UserModel.find({ imageNumber: this.imageNumber });
        return records.length == 0;
    }

    /**
     * @public
     * @returns {Promise<EvaluationResult>} 
     */
    async evaluate() {
        if (this.isLessThanMinimum()) {
            return new EvaluationResult(false, `This image number is off the minimum bound(${ImageNumberValidator.MINIMUM_IMAGE_NUMBER}).`);
        }

        if (this.isMoreThanMaximum()) {
            return new EvaluationResult(false, `This image number is off the maximum bound(${ImageNumberValidator.MAXIMUM_IMAGE_NUMBER}).`);
        }

        if (!(await this.isImageNumberAvailable())) {
            return new EvaluationResult(false, "This image number is unavailable");
        }

        return new EvaluationResult(true);
    }
}