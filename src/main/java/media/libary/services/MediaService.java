package media.libary.services;

import java.util.List;
import media.libary.repository.model.ShowModel;

public interface MediaService {
  /**
   * Retrieves a listing of all the available shows.
   * @return The collection of shows. If not shows are found, an empty collection is returned.
   */
  List<ShowModel> allShows();

  /**
   * Retrieves the requested show by it's unique id.
   * @param id The unique id of the show.
   * @return The show if found, others returns null.
   */
  ShowModel getShow(Long id);
}
