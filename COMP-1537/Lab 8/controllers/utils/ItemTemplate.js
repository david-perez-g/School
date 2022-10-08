const { ItemModel } = require("../../models/itemModel");
const { StringDecoder } = require("string_decoder");

exports.ItemTemplate = class ItemTemplate {
    /**
     * 
     * @param {String} text 
     * @param {String | undefined} creatorUsername
     */
    constructor(text, creatorUsername = undefined) {
        this.text = text;
        this.creatorUsername = creatorUsername;
    }

    save() {
        return new ItemModel({
            text: this.text,
            creatorUsername: this.creatorUsername
        }).save();
    }

    /**
     * Returns the item formatted as an html li tag.
     * For example:
     * <li>Do Work</li>
     */
    makeLiTag() {
        return `<li>${this.text}</li>`
    }

    /**
     * 
     * @param {Uint8Array[]} buffer 
     * @param {String | undefined} username 
     * @returns {ItemTemplate}
     */
    static buildFromBuffer(buffer, username = undefined) {
        const data = Buffer.concat(buffer).toString("utf-8");
        const itemText = data.slice(5).trim();
        return new ItemTemplate(itemText, username);
    }

    /**
     * 
     * @param {{text:String, creatorUsername:String|undefined}} record 
     */
    static buildFromRecord(record) {
        return new ItemTemplate(record.text, record.creatorUsername);
    }
}