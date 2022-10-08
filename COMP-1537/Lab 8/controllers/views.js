const { readFileSync } = require("fs");
const { exit } = require("process");

const ENCODING = "utf-8";
const EXIT_FAILURE = 1;
const ERR_LOADING_FILE = "Error while loading file ";

const loginPageHtmlPath = "./views/login.html";
const signupPageHtmlPath = "./views/signup.html";
const error404PageHtmlPath = "./views/404.html";
const personalItemsPageHtmlPath = "./views/my-items.html";
const communityItemsPageHtmlPath = "./views/community-items.html";

try {
    exports.loginPageContent = readFileSync(loginPageHtmlPath, ENCODING).toString();
} catch (error) {
    console.log(ERR_LOADING_FILE + loginPageHtmlPath);
    exit(EXIT_FAILURE);
}

try {
    exports.signupPageContent = readFileSync(signupPageHtmlPath, ENCODING).toString();
} catch (error) {
    console.log(ERR_LOADING_FILE + signupPageHtmlPath);
    exit(EXIT_FAILURE);
}

try {
    exports.error404PageContent = readFileSync(error404PageHtmlPath, ENCODING).toString();
} catch (error) {
    console.log(ERR_LOADING_FILE + error404PageHtmlPath);
    exit(EXIT_FAILURE);
}

try {
    exports.personalItemsPageContent = readFileSync(personalItemsPageHtmlPath, ENCODING).toString();
} catch (error) {
    console.log(ERR_LOADING_FILE + personalItemsPageHtmlPath);
    exit(EXIT_FAILURE);
}

try {
    exports.communityItemsPageContent = readFileSync(communityItemsPageHtmlPath, ENCODING).toString();
} catch (error) {
    console.log(ERR_LOADING_FILE + personalItemsPageHtmlPath);
    exit(EXIT_FAILURE);
}