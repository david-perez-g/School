const mongoose = require("mongoose");
const { UserModel: User } = require("./userModel");

const AT_COLLECTION = "items";
const MODEL_NAME = "Item";
const { Types } = require("mongoose");

const itemSchema = new mongoose.Schema({
    creatorUsername: String,
    text: String
})

exports.ItemModel = mongoose.model(MODEL_NAME, itemSchema, AT_COLLECTION);