const { IncomingMessage, ServerResponse } = require("http");
const { ItemModel } = require("../models/itemModel");
const { ItemTemplate } = require("./utils/ItemTemplate");
const { joinLiTags } = require("./myItemsController")
const { communityItemsPageContent } = require("./views")

/**
 * @returns {Promise<ItemTemplate[]>}
 */
async function getAllItems() {
    const result = [];
    const records = await ItemModel.find();
    for (const record of records) {
        //@ts-ignore
        result.push(ItemTemplate.buildFromRecord(record));
    }

    return result;
}

/**
 * 
 * @param {IncomingMessage} _ 
 * @param {ServerResponse} response 
 */
exports.getCommunityItems = async function(_, response) {
    const items = await getAllItems();
    response.writeHead(200, { "Content-Type": "text/html" });
    response.write(communityItemsPageContent.replace("{{items}}", joinLiTags(items)));
    response.end();
}

/**
 * 
 * @param {IncomingMessage} request 
 * @param {ServerResponse} response 
 */
exports.postCommunityItems = function(request, response) {
    const buffer = [];

    request.on("data", chunk => buffer.push(chunk));
    request.on("close", () => {
        const item = ItemTemplate.buildFromBuffer(buffer);
        item.save();
        response.writeHead(303, { Location: "/community-items" });
        response.end();
    })

}