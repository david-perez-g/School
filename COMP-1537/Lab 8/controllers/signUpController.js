const { IncomingMessage, ServerResponse } = require("http");
const { UserTemplate } = require("./utils/UserTemplate");
const { SignUpValidator } = require("./signUpUtils/SignUpValidator");
const { signupPageContent } = require("./views")

/**
 * 
 * @param {IncomingMessage} _
 * @param {ServerResponse} response 
 */
function getSignUpPage(_, response, errorMessage = "") {
    response.writeHead(200, { "Content-Type": "text/html" });
    response.write(signupPageContent.replace("{{error-message}}", errorMessage));
    response.end();
}

/**
 * 
 * @param {IncomingMessage} request
 * @param {ServerResponse} response 
 */
async function postSignUp(request, response) {
    const buffer = [];

    request.on("data", chunk => buffer.push(chunk))
    request.on("close", async() => {
        const user = UserTemplate.buildFromSignUpBuffer(buffer);
        const validation = await SignUpValidator.test(user);

        if (!validation.result) {
            getSignUpPage(request, response, validation.message);
        } else {
            user.save();
            response.writeHead(303, { Location: "/login" })
            response.end();
        }
    })
}

exports.getSignUpPage = getSignUpPage;
exports.postSignUp = postSignUp;