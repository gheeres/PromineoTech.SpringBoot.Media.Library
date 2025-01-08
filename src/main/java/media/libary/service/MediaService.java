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
   * Get's a show by its unique identifier.
   * @param showId The unique identifier of the show.
   * @return The show if found, otherwise returns null;
   */
  ShowModel getShowById(Long showId);
  
  /**
   * Searches a show by the specified parameters.
   * @param name The name or part of the name of the show to search.
   * @return The shows that match if any, otherwise an empty list.
   */
  List<ShowModel> searchShows(String name);
}
 