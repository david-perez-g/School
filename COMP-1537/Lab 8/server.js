const { createServer, IncomingMessage, ServerResponse } = require("http");
const NodeSession = require("node-session");
const mongoose = require("mongoose");
const { abort } = require("process");
const controllers = require("./controllers/controllers");

const SESSION_SECRET_KEY = "asjnabcUasCJAJCN,XMN CNao";
const SESSION_LIFETIME = 3000000;

const session = new NodeSession({ "secret": SESSION_SECRET_KEY, "lifetime": SESSION_LIFETIME });

const SERVER_PORT = 8080;
const DB_NAME = "todo-app-db";
const DB_URL = `mongodb://localhost:27017/${DB_NAME}`;

mongoose.connect(DB_URL).catch((reason) => {
    console.log(
        "\nAn error ocurred while connecting to the database at (" + DB_URL + ")\n"
    );
    console.error(reason);
    abort();
});

console.log("\nConnection with the database was successful");

/**
 * Handlers for GET requests.
 * 
 * @enum {function(IncomingMessage, ServerResponse):void}
 * @readonly
 */
const getHandlers = {};
getHandlers["/"] = (req, res) => {
    // user not logged in
    if (!req.session.get("username")) {
        controllers.temporarilyRedirectTo(req, res, "/login");
    } else {
        controllers.temporarilyRedirectTo(req, res, "/my-items");
    }

};
getHandlers["/login"] = controllers.getLoginPage;
getHandlers["/signup"] = controllers.getSignUpPage;
getHandlers["/404"] = controllers.get404Page;
getHandlers["/my-items"] = controllers.getPersonalItems;
getHandlers["/community-items"] = controllers.getCommunityItems;
getHandlers["/logout"] = controllers.handleLogout;

/**
 * Handlers for POST requests.
 * 
 * @enum {function(IncomingMessage, ServerResponse):void}
 * @readonly
 */
const postHandlers = {};
postHandlers["/login"] = controllers.postLogin;
postHandlers["/signup"] = controllers.postSignUp;
postHandlers["/404"] = controllers.get404Page;
postHandlers["/my-items"] = controllers.postPersonalItems;
postHandlers["/community-items"] = controllers.postCommunityItems;

createServer((req, res) => {
    session.startSession(req, res, () => {
        const method = req.method;
        const route = req.url;
        const handlers = method == "GET" ? getHandlers : postHandlers;
        const handler = handlers[route] || handlers["/404"];
        handler(req, res);
    })
}).listen(SERVER_PORT);

console.log("Server succesfully started at localhost:" + SERVER_PORT);