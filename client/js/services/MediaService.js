const DefaultBaseUrl = 'http://localhost:8080';

export default class WorldService {
  constructor(baseUrl) {
    this.baseUrl = baseUrl || DefaultBaseUrl;
  }

  /**
   * Retrieves the list of recently watched shows.
   * @returns {Promise{Object}} The promise contains the json response.
   */
  getRecentlyWatchedShows = () => {
    return Promise.all([
      this.getShow(1),
      this.getShow(6),
    ]);
  }

  /**
   * Retrieves all shows.
   * @returns {Promise{Object}} The promise contains the json response.
   */
  getAllShows = () => {
    const url = `${ this.baseUrl }/shows`;
    return fetch(url).then((response) => response.json());
  }

  /**
   * Retrieves the show with the specified unique id.
   * @param {number} showId The unique id of the show to retrieve.
   * @returns {Promise{Object}} The promise contains the json response.
   */
  getShow = (showId) => {
    const url = `${ this.baseUrl }/shows/${ showId }`;
    return fetch(url).then((response) => response.json());
  }

  /**
   * Retrieves the url to retrieve the show poster.
   * @param {number} showId The unique id of the show to retrieve.
   * @returns {string} The url that the browser can use to render the show.
   */
  getShowPosterUrl = (showId) => {
    return `${ this.baseUrl }/shows/${ showId }/poster/raw`;
  }

  /**
   * Retrieves the top or main actors for a show.
   * @param {number} showId The unique id of the show to retrieve.
   * @param {number} limit The maximum number to return. If not specified, defaults to 10.
   * @returns {Promise{Object}} The promise contains the json response.
   */
  getTopActorsForShow = (showId, limit) => {
    limit = limit || 10;
    const url = `${ this.baseUrl }/shows/${ showId }/actors`;
    return fetch(url).then((response) => response.json()).then((actors) => {
      if (actors.length <= limit) {
        return actors;
      }
      return actors.slice(0, limit);
    });
  }

  /**
   * Retrieves all of the available episodes for a show.
   * @param {number} showId The unique id of the show to retrieve.
   * @returns {Promise{Object}} The promise contains the json response.
   */
  getEpisodesForShow = (showId) => {
    const url = `${ this.baseUrl }/shows/${ showId }/episodes`;
    return fetch(url).then((response) => response.json());
  }

  /**
   * Retrieves the url to retrieve the actor default photo.
   * @param {number} actorId The unique id of the actor to retrieve.
   * @returns {string} The url that the browser can use to render the actor.
   */
  getActorPhotoUrl = (actorId) => {
    return `${ this.baseUrl }/actors/${ actorId }/photo/current/raw`;
  }  

  /**
   * Retrieves the actor with the specified unique id.
   * @param {number} actorId The unique id of the actor to retrieve.
   * @returns {Promise{Object}} The promise contains the json response.
   */
  getActor = (actorId) => {
    const url = `${ this.baseUrl }/actors/${ actorId }`;
    return fetch(url).then((response) => response.json());
  }
};