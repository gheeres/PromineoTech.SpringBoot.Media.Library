import { createHTMLElement, createLoadingHTMLOverlay } from './_utils.js';
import MediaService from './services/MediaService.js';

document.addEventListener('DOMContentLoaded', function () {
  const searchParams = new URLSearchParams(window.location.search);
  const actorId = searchParams.get('actorId');
  
  const service = new MediaService();
  const main = document.querySelector('main');
  const summary = document.querySelector('.actor-summary');
  service.getActor(actorId).then((actor) => {
    document.title = `Actors: ${ actor.name }`;
    summary.prepend(
      createHTMLElement(`<img src="${ service.getActorPhotoUrl(actor.id) }" alt="${ actor.name }" />`),
      createHTMLElement(`
        <div>
          <h1>${ actor.name }</h1>
          <h2>Biography</h2>
          <p>${ actor.description || '' }</p>
        </div>
      `),
    );

    if (actor.shows) {
      main.append(createHTMLElement(`<h2>TV Roles</h2>`));
      const list = createHTMLElement(`<ul class="list tv-list"></ul>`);
      for(const show of actor.shows) {
        list.append(createHTMLElement(`
          <li>
            <a href="show.html?showId=${ show.id }">
              <img src="${ service.getShowPosterUrl(show.id) }" alt="${ show.name }" />
              <span>${ show.name }</span>
            </a> 
          </li>     
        `));
      }
      main.append(list);
    }
  });
}, false);