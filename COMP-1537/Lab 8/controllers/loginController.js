const { IncomingMessage, ServerResponse } = require("http");
const { UserTemplate } = require("./utils/UserTemplate");
const { LoginAuthenticator } = require("./loginUtils/LoginAuthenticator");
const { loginPageContent } = require("./views");

/**
 * 
 * @param {IncomingMessage} _
 * @param {ServerResponse} response 
 * @param {String} errorMessage error message to be sent to the message
 */
function getLoginPage(_, response, errorMessage = "") {
    response.writeHead(200, { "Content-Type": "text/html" });
    response.write(loginPageContent.replace("{{error-message}}", errorMessage));
    response.end();
}

/**
 * 
 * @param {IncomingMessage} request
 * @param {ServerResponse} response 
 */
async function postLogin(request, response) {
    const buffer = [];

    request.on("data", chunk => buffer.push(chunk))
    request.on("close", async() => {
        const user = UserTemplate.buildFromLoginBuffer(buffer);
        const authentication = await (new LoginAuthenticator(user)).evaluate();
        if (!authentication.result) {
            getLoginPage(request, response, authentication.message);
        } else {
            request.session.put("username", user.username);
            response.writeHead(303, { Location: "/my-items" });
            response.end();
        }
    })
}

exports.getLoginPage = getLoginPage;
exports.postLogin = postLogin;