exports.EvaluationResult = class EvaluationResult {
    /**
     * 
     * @param {boolean} result 
     * @param {String | null} message message to the end user about the failure
     */
    constructor(result, message = null) {
        this.result = result;
        this.message = message;
    }
}