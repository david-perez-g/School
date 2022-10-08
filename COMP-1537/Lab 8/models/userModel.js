const mongoose = require("mongoose");
const bcrypt = require("bcrypt");

const AT_COLLECTION = "users";
const MODEL_NAME = "User"

const userSchema = new mongoose.Schema({
    username: String,
    password: String,
    imageNumber: Number
})

exports.UserModel = mongoose.model(MODEL_NAME, userSchema, AT_COLLECTION);