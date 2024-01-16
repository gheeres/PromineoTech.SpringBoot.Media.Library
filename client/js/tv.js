import MediaService from './services/MediaService.js';
import { createHTMLElement } from './_utils.js';

document.addEventListener('DOMContentLoaded', function () {
  const service = new MediaService();

  const list = document.querySelector('.tv-list');
  service.getAllShows().then((shows) => {
    shows.forEach((show) => {
      const listItem = createHTMLElement(`<li>
        <a href="show.html?showId=${ show.id }">
          <img src="${ service.getShowPosterUrl(show.id) }" title="${ show.name }" />
          ${ show.name }
        </a> 
      </li>`);        
      list.appendChild(listItem);
    });
  });
}, false);