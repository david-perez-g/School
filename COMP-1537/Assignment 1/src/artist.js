import { artistExists, getArtist } from "./artist_data.mjs";

const artistSection = document.getElementsByTagName("section")[0];

class Artist {
  /**
   * @param {Number} number
   * @param {String} name
   * @param {String} title
   * @param {String} about
   */
  constructor(number, name, title, about) {
    this.number = number;
    this.name = name;
    this.title = title;
    this.about = about;
  }
}

/**
 *
 * @param {Artist} artist
 */
function addArtistToHTML(artist) {
  const artistImg = document.createElement("img");
  artistImg.setAttribute(
    "src",
    `https://randomuser.me/api/portraits/men/${artist.number}.jpg`
  );

  artistImg.setAttribute("class", "artist-picture");

  const artistNameTag = document.createElement("h1");
  artistNameTag.setAttribute("class", "artist-name");
  artistNameTag.textContent = artist.name;

  const artistTitleTag = document.createElement("p");
  artistTitleTag.setAttribute("class", "artist-title");
  artistTitleTag.textContent = artist.title;

  const aboutArtistTag = document.createElement("p");
  aboutArtistTag.setAttribute("class", "about-artist");
  aboutArtistTag.textContent = artist.about;

  artistSection.appendChild(artistImg);
  artistSection.appendChild(artistNameTag);
  artistSection.appendChild(artistTitleTag);
  artistSection.appendChild(aboutArtistTag);
}

function main() {
  let params = new URL(document.location).searchParams;
  let artistNum = params.get("num");
  let artistName = params.get("name");

  if (!artistExists(artistNum)) {
    alert("This artist number does not exist");
    return;
  }
  const artistInfo = getArtist(artistNum);
  const artist = new Artist(
    artistNum,
    artistName,
    artistInfo.title,
    artistInfo.about
  );
  addArtistToHTML(artist);
}

main();
