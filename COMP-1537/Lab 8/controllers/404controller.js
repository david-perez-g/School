const { IncomingMessage, ServerResponse } = require("http");
const { error404PageContent } = require("./views");

/**
 * 
 * @param {IncomingMessage} _
 * @param {ServerResponse} response 
 */
exports.get404Page = function(_, response) {
    response.writeHead(404, { "Content-Type": "text/html" });
    response.write(error404PageContent);
    response.end();
}