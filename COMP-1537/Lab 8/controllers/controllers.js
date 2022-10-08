const { get404Page } = require("./404controller");
const { getLoginPage, postLogin } = require("./loginController");
const { getSignUpPage, postSignUp } = require("./signUpController");
const { getPersonalItems, postPersonalItems } = require("./myItemsController");
const { getCommunityItems, postCommunityItems } = require("./communityItemsController");
const { IncomingMessage, ServerResponse } = require("http");

/**
 * 
 * @param {IncomingMessage} request
 * @param {ServerResponse} response 
 */
exports.handleLogout = (request, response) => {
    if (request.session.has("username")) {
        request.session.forget("username");
    }

    this.temporarilyRedirectTo(request, response, "/login");
}

/**
 * 
 * @param {IncomingMessage} _ 
 * @param {ServerResponse} response 
 * @param {String} route route to be redirected to
 */
exports.temporarilyRedirectTo = (_, response, route) => {
    response.writeHead(303, { Location: route });
    response.end();
};

/**
 * 
 * @param {IncomingMessage} _ 
 * @param {ServerResponse} response 
 * @param {String} route route to be redirected to
 */
exports.permanentlyRedirectTo = (_, response, route) => {
    response.writeHead(301, { Location: route });
    response.end();
};

//get handlers
exports.get404Page = get404Page;
exports.getLoginPage = getLoginPage;
exports.getSignUpPage = getSignUpPage;
exports.getPersonalItems = getPersonalItems;
exports.getCommunityItems = getCommunityItems;

//post handlers
exports.postLogin = postLogin;
exports.postSignUp = postSignUp;
exports.postPersonalItems = postPersonalItems;
exports.postCommunityItems = postCommunityItems;