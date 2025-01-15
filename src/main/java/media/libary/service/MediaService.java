package media.libary.service;

import java.util.List;
import media.libary.repository.models.ShowModel;

public interface MediaService {
  /**
   * Retrieves all available shows.
   * @return The collection of shows.
   */
  List<ShowModel> getAllShows();
  
  /**
   * Retrieves a show by it's unique identifier.
   * @param showId The unique id.
   * @return The show if found, otherwise returns null.
   */
  ShowModel getShowById(Long showId);
  
  /**
   * Updates or sets the poster to use for the specified show.
   * @param showId The unique id of the show.
   * @param contentType The mime type of the content.
   * @param poster The binary image / content.
   * @return The resulting image.
   */
  byte[] setPosterForShow(Long showId, String contentType, byte[] poster);
}
