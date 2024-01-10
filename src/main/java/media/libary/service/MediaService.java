package media.libary.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import media.libary.repository.ActorRepository;
import media.libary.repository.EpisodeRepository;
import media.libary.repository.ShowRepository;
import media.libary.repository.model.ShowModel;
import media.libary.service.model.CreateShowInput;
import media.libary.service.model.Show;

@Service
public class MediaService {
  private ShowRepository showRepository;
  private EpisodeRepository episodeRepository;
  private ActorRepository actorRepository;
  
  public MediaService(ShowRepository showRepository, EpisodeRepository episodeRepository,
                      ActorRepository actorRepository) {
    this.showRepository = showRepository;
    this.episodeRepository = episodeRepository;
    this.actorRepository = actorRepository;
  }
  
  private ShowModel toShowModel(CreateShowInput input) {
    ShowModel result = new ShowModel();
    result.setShow_name(input.getShow_name());
    result.setShow_description(input.getShow_description());
    return result;
  }
  
  private Show toShow(ShowModel model) {
    Show result = new Show();
    result.setId(model.getShow_id());
    result.setName(model.getShow_name());
    result.setDescription(model.getShow_description());
    return result;
  }
  
  public Response<Show> createShow(CreateShowInput input) {
    ShowModel model = toShowModel(input);
    if (model != null) {
      ShowModel result = showRepository.save(model);
      return Response.OK(toShow(result));
    }
    return Response.Error(500, "Failed to save due to an unhandled data access layer error.");
  }
  
  /**
   * Retrieves all of the available shows.
   * @return The collection of shows.
   */
  public List<Show> getAllShows() {
    List<ShowModel> shows = showRepository.findAll();
    //List<Show> result = new ArrayList<Show>();
    //for(ShowModel model: shows) {
    //  Show show = toShow(model);
    //  if (show != null) {
    //    result.add(show);
    //  }
    //}
    // return result;
    
    return shows.stream().map(m -> {
      return toShow(m);
    }).toList();
  }
}
