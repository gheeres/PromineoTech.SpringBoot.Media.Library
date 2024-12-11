package media.libary.service;

import java.util.List;
import media.libary.repository.models.ShowModel;

public interface MediaService {
  /**
   * Retrieves all available shows.
   * @return The collection of shows.
   */
  List<ShowModel> getAllShows();
}
