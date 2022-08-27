import { artistExists } from "./artist_data.mjs";

class Artist {
  /**
   *
   * @param {String} name artist name
   * @param {String} about information about the artist
   * @param {Number} number artist number
   */
  constructor(name, about, number) {
    this.name = name;
    this.about = about;
    this.number = number;
  }
}

const addArtistButton = document.getElementById("add-artist-btn");

const addArtistForm = document.getElementById("add-artist-form");
const artistNameInput = document.getElementById("artist-name-input");
const aboutArtistInput = document.getElementById("about-artist-input");
const artistNumberInput = document.getElementById("artist-number-input");
const submitArtistButton = document.getElementById("submit-artist-btn");

const artistSection = document.getElementById("artists");

/**
 * @param {HTMLInputElement} inputTag
 */
function getInputValue(inputTag) {
  return inputTag.value;
}

/**
 * @param {Artist} artist artist to be added to the section of artists
 */
function addArtistToHTML(artist) {
  const artistPageLink = `./artist.html?num=${artist.number}&name=${artist.name}`;

  const artistCard = document.createElement("div");
  artistCard.setAttribute("class", "artist-card");

  const artistPictureContainer = document.createElement("div");
  artistPictureContainer.setAttribute("class", "artist-picture-container");

  const artistImg = document.createElement("img");
  artistImg.setAttribute("class", "artist-picture");
  artistImg.setAttribute(
    "src",
    `https://randomuser.me/api/portraits/thumb/men/${artist.number}.jpg`
  );

  const artistInformation = document.createElement("div");
  artistInformation.setAttribute("class", "artist-information");

  const artistName = document.createElement("a");
  artistName.setAttribute("href", artistPageLink);
  artistName.setAttribute("class", "artist-name");
  artistName.textContent = artist.name;

  const aboutArtist = document.createElement("a");
  aboutArtist.setAttribute("href", artistPageLink);
  aboutArtist.setAttribute("class", "artist-description");
  aboutArtist.textContent = artist.about;

  artistInformation.appendChild(artistName);
  artistInformation.appendChild(aboutArtist);

  artistPictureContainer.appendChild(artistImg);

  artistCard.appendChild(artistPictureContainer);
  artistCard.appendChild(artistInformation);

  artistSection.appendChild(artistCard);
}

function main() {
  addArtistButton.addEventListener("click", function () {
    //Changing artist form visibility
    if (addArtistForm.style.display === "flex") {
      addArtistForm.style.display = "none";
      return;
    }

    addArtistForm.style.display = "flex";
  });

  submitArtistButton.addEventListener("click", function () {
    const artistName = getInputValue(artistNameInput);
    const aboutArtist = getInputValue(aboutArtistInput);
    const artistNumber = parseInt(getInputValue(artistNumberInput));

    if (isNaN(artistNumber)) {
      alert("Please insert a correct artist number");
      return;
    }

    if (!artistExists(artistNumber)) {
      alert("This artist number does not exist");
      return;
    }

    const artist = new Artist(artistName, aboutArtist, artistNumber);
    addArtistToHTML(artist);
  });

  //service worker setup

  if ("serviceWorker" in navigator) {
    navigator.serviceWorker
      .register("../sw.js")
      .then(() => console.log("ServiceWorker started successfully"))
      .catch((err) =>
        console.log("Couldn't register service worker: " + err.message)
      );
  }
}

main();
