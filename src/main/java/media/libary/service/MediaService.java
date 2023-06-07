package media.libary.service;

import java.util.List;
import org.springframework.stereotype.Service;
import media.libary.controller.model.CreateShowInput;
import media.libary.controller.model.Show;
import media.libary.exception.InvalidInputException;
import media.libary.exception.SaveException;
import media.libary.exception.ShowNotFoundException;
import media.libary.repository.ActorRepository;
import media.libary.repository.EpisodeRepository;
import media.libary.repository.ShowRepository;
import media.libary.repository.model.ShowModel;

@Service
public class MediaService {
  // @AutoWired
  private ShowRepository showRepository;
  
  // @AutoWired
  private ActorRepository actorRepository;
  
  // @AutoWired
  private EpisodeRepository episodeRepository;
  
  public MediaService(ShowRepository showRepository, ActorRepository actorRepository,
                      EpisodeRepository episodeRepository) {
    this.showRepository = showRepository;
    this.actorRepository = actorRepository;
    this.episodeRepository = episodeRepository;
  }
  
  public List<Show> getAllShows() {
    List<ShowModel> models = showRepository.findAll();
    
    //List<Show> shows = new ArrayList<Show>();
    //for(ShowModel model : models) {
    //  Show show = Convert.toShow(model);
    //  if (show != null) {
    //    shows.add(show);
    //  }
    //}
    //return shows; 

    return models.stream().map(m -> {
      return Convert.toShow(m);
    }).toList();
  }

  /**
   * Creates a new show.
   * @param input The show
   * @return The show if created, otherwise returns null.
   */
  public Show createShow(CreateShowInput input) {
    ShowModel model = Convert.toShowModel(input);
    if (model != null) {
      ShowModel result = showRepository.save(model);
      if (result != null) {
        return Convert.toShow(result);
      }
      throw new SaveException(model);
    }
    throw new InvalidInputException(input);
  }

  public Show getShow(Long showId) {
    ShowModel model = showRepository.findById(showId).orElseThrow(() -> {
      throw new ShowNotFoundException(showId);
    });
    return Convert.toShow(model);
  }
}
