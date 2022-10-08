const { IncomingMessage, ServerResponse } = require("http");
const { ItemModel } = require("../models/itemModel");
const { personalItemsPageContent } = require("./views");
const { ItemTemplate } = require("./utils/ItemTemplate");
const { UserModel } = require("../models/userModel");

/**
 * @param {String} username username of the user
 * @returns {Promise<ItemTemplate[]>}
 */
async function getUserItems(username) {
    const items = [];

    const recordItems = await ItemModel.find({ creatorUsername: username });
    for (const item of recordItems) {
        items.push(new ItemTemplate(item.text || "", username));
    }

    return items;
}

/**
 *
 * @param {String} username 
 * @returns {Promise<Number | undefined>} the image number of the user
 */
async function getUserImageNumber(username) {
    const usrRecord = await UserModel.findOne({ username: username });
    if (!usrRecord) {
        throw new Error("Got an inexistent user at getUserImageNumber");
    }

    return usrRecord.imageNumber;
}

/**
 * 
 * @param {ItemTemplate[]} items 
 * @returns {String}
 */
function joinLiTags(items) {
    let result = "";
    for (const item of items) {
        result += item.makeLiTag();
    }
    return result;
}

exports.joinLiTags = joinLiTags;

/**
 * 
 * @param {IncomingMessage} request 
 * @param {ServerResponse} response 
 */
exports.getPersonalItems = async function(request, response) {
    const username = request.session.get("username");

    // user not authenticated
    if (!username) {
        response.writeHead(303, { Location: "/login" });
        return response.end();
    }

    const userItems = await getUserItems(username);
    const userImageNumber = (await getUserImageNumber(username)) || 0;

    const page = personalItemsPageContent
        .replace("{{items}}", joinLiTags(userItems))
        .replace("{{imageNumber}}", userImageNumber.toString());

    response.writeHead(200, { "Content-Type": "text/html" });
    response.write(page);
    response.end();
}

/**
 * 
 * @param {IncomingMessage} request 
 * @param {ServerResponse} response 
 */
exports.postPersonalItems = function(request, response) {
    const buffer = [];

    request.on("data", chunk => buffer.push(chunk));
    request.on("close", () => {
        const username = request.session.get("username");
        const item = ItemTemplate.buildFromBuffer(buffer, username);
        item.save();
        response.writeHead(303, { Location: "/my-items" });
        response.end();
    })

}