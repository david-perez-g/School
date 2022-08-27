const cacheName = "artist_dir sw";
const files = [
  "./styles/artist.css",
  "./src/artist_data.mjs",
  "./artist.html",
  "./src/artist.js",
  "./styles/index.css",
  "./index.html",
  "./src/index.js",
  "./sw.js",
  "./",
];

self.addEventListener("install", function () {
  caches
    .open(cacheName)
    .then(function (cache) {
      cache.addAll(files).catch((err) => console.log(err.message));
    })
    .catch(function (err) {
      console.log("Unable to open cache " + err.message);
    });
});

self.addEventListener("fetch", function (event) {
  event.respondWith(
    caches.match(event.request).then(function (match) {
      if (match) {
        return match;
      }
      return fetch(event.request).then(function (response) {
        return caches.open(cacheName).then(function (cache) {
          cache.put(event.request, response.clone());
          return response;
        });
      });
    })
  );
});
