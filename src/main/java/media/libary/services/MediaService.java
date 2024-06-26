package media.libary.services;

import java.util.List;
import media.libary.models.Image;
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
  
  /**
   * Retrieve the photo for the specified show.
   * @param id The unique id of the show.
   * @return The binary representation / raw data for the photo.
   */
  Image getPoster(Long id);
  
  /**
   * Updates or changes the poster for the specified show.
   * @param id The unique id of the show.
   * @param poster The show image/ contents.
   * @return The resulting image / content stored in the database.
   */
  Image setPoster(Long id, Image poster);
  
  /**
   * Checks to see if the show is valid.
   * @param id The unique if of the show to validate.
   * @return True if found, false if otherwise.
   */
  boolean isValidShow(Long id);
}
