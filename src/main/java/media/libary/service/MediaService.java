package media.libary.service;

import org.springframework.stereotype.Service;
import media.libary.controller.model.CreateShowInput;
import media.libary.controller.model.Show;
import media.libary.repository.ShowRepository;
import media.libary.repository.model.ShowModel;

@Service
public class MediaService {
  private ShowRepository showRepository;
  
  public MediaService(ShowRepository showRepository) {
    this.showRepository = showRepository;
  }
  
  public Response<Show> createShow(CreateShowInput input) {
    ShowModel result = showRepository.save(Convert.toShowModel(input));
    if (result != null) {
      return Response.OK(Convert.toShow(result), "Show created.");
    }
    return Response.Error("Failed to create requested show. No input provided.");
  }
  
  /**
   * Inner class to use for mapping objects to and from external types.
   */
  private abstract class Convert {
    private static ShowModel toShowModel(CreateShowInput input) {
      ShowModel result = new ShowModel();
      result.setShow_name(input.getName());
      result.setShow_description(input.getDescription());
      return result;
    }

    private static Show toShow(ShowModel show) {
      Show result = new Show();
      result.setId(show.getShow_id());
      result.setName(show.getShow_name());
      result.setDescription(show.getShow_description());
      return result;
    }
  }
}