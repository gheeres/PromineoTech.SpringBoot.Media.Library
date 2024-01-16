import { createHTMLElement, createLoadingHTMLOverlay } from './_utils.js';
import MediaService from './services/MediaService.js';

document.addEventListener('DOMContentLoaded', function () {
  const searchParams = new URLSearchParams(window.location.search);
  const showId = searchParams.get('showId');

  const service = new MediaService();
  const main = document.querySelector('main');

  main.style.visibility = 'hidden';
  const loading = createLoadingHTMLOverlay();
  main.parentNode.append(loading);

  service.getShow(showId).then((show) => {
    document.title = `TV: ${ show.name }`;
    
    main.append(
      createHTMLElement(`
        <div class="summary tv-summary">
          <img src="${ service.getShowPosterUrl(show.id) }" alt="${ show.name }" />
          <div>
            <h1>${ show.name }</h1> 
            <h2>Plot / Summary</h2>
            <p>${ show.description }</p>
          </div>
        </div>
      `),
     );

    Promise.all([
      service.getTopActorsForShow(show.id).then((actors) => {
        if (actors.length) {
          main.append(createHTMLElement(`<h2>Top Cast</h2>`));
          const list = createHTMLElement(`<ul class="list list-tv-cast"></ul>`);
          actors.forEach((actor) => {
            list.append(createHTMLElement(`
              <li>
                <a href="actor.html?actorId=${ actor.id }">
                  <img src="${ service.getActorPhotoUrl(actor.id) }" alt="${ actor.name }" />
                  <span>${ actor.name }</span>
                </a> 
                <span>${ actor.name }</span> 
              </li>
            `));
          });
          main.append(list);
        }
      }),
      service.getEpisodesForShow(show.id).then((episodes) => {
        if (episodes.length) {
          main.append(createHTMLElement(`<h2>Episodes</h2>`));
          const seasonList = createHTMLElement(`<div class="list list-tv-season"></div>`);
          main.append(seasonList);
         
          const seasons = Object.groupBy(episodes, (episode) => episode.season);
          for (const [season, episodesBySeason] of Object.entries(seasons)) {
            seasonList.append(
              createHTMLElement(`<h3>Season ${ season }</h3>`),
              createHTMLElement(`
                <table class="table table-tv-episode">
                  <thead>
                    <tr>
                      <th scope="col">No.</th> 
                      <th scope="col">Title</th>
                      <th scope="col">Description</th>
                    </tr>
                  </thead>
                  <tbody>
                  ${
                    episodesBySeason.map((episode) => {
                      return `<tr>
                                <td>${ episode.episode }</td>
                                <td>${ episode.name }</td>
                                <td>${ episode.description }</td>
                              </tr>`;
                    })
                  }
                  </tbody>
                </table>
              `)
            );
          }
        }
      }),
    ]).then(() => {
      loading.remove();
      main.style.visibility = 'visible';
    });
  });
}, false);