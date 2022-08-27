const chatSection = document.getElementsByTagName("section")[0];

const sendBtns = document.getElementsByTagName("button");
const leftUsrSendBtn = sendBtns[0];
const rightUsrSendBtn = sendBtns[1];

const textAreas = document.getElementsByTagName("textarea");
const leftUsrTextArea = textAreas[0];
const rightUsrTextArea = textAreas[1];

const usrPhotos = document.getElementsByTagName("img");
const leftUsrPhotoSource = usrPhotos[0].getAttribute("src");
const rightUsrPhotoSource = usrPhotos[1].getAttribute("src");

const User = {
  LEFT: 0,
  RIGHT: 1,
};

function getTextAreaContent(user) {
  if (user === User.LEFT) {
    text = leftUsrTextArea.value.trim();
    leftUsrTextArea.value = "";
    return text;
  }

  text = rightUsrTextArea.value.trim();
  rightUsrTextArea.value = "";
  return text;
}

function sendMessage(user, text) {
  let containerClassName = "message";

  if (user === User.RIGHT) {
    containerClassName += " right-message";
  }

  let usrPictureSource =
    user === User.LEFT ? leftUsrPhotoSource : rightUsrPhotoSource;

  const messageContainer = document.createElement("div");
  messageContainer.setAttribute("class", containerClassName);

  const usrPhoto = document.createElement("img");
  usrPhoto.setAttribute("src", usrPictureSource);

  const messageText = document.createElement("p");
  messageText.textContent = text;

  const deleteBtn = document.createElement("button");
  deleteBtn.setAttribute("class", "delete-btn");
  deleteBtn.textContent = "X";

  deleteBtn.addEventListener("click", onDeleteMessageClick);

  messageContainer.appendChild(usrPhoto);
  messageContainer.appendChild(messageText);
  messageContainer.appendChild(deleteBtn);

  chatSection.appendChild(messageContainer);
}

function onDeleteMessageClick(event) {
  event["target"].parentNode.remove();
}

function onLeftBtnClick() {
  sendMessage(User.LEFT, getTextAreaContent(User.LEFT));
}

function onRightBtnClick() {
  sendMessage(User.RIGHT, getTextAreaContent(User.RIGHT));
}

function main() {
  leftUsrSendBtn.addEventListener("click", onLeftBtnClick);
  rightUsrSendBtn.addEventListener("click", onRightBtnClick);
}

main();
