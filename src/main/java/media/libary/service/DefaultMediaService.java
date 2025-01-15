package media.libary.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import media.libary.repository.ActorRepository;
import media.libary.repository.EpisodeRepository;
import media.libary.repository.ShowRepository;
import media.libary.repository.models.ShowModel;

@Service
public class DefaultMediaService implements MediaService {
  private ActorRepository actorRepository;
  private ShowRepository showRepository;
  private EpisodeRepository episodeRepository;
  
  public DefaultMediaService(ActorRepository actorRepository, ShowRepository showRepository, EpisodeRepository episodeRepository) {
    this.actorRepository = actorRepository;
    this.showRepository = showRepository;
    this.episodeRepository = episodeRepository;
  }

  @Override
  public List<ShowModel> getAllShows() {
    List<ShowModel> shows = showRepository.findAll();
    return shows;
  }

  @Override
  public ShowModel getShowById(Long showId) {
     Optional<ShowModel> show = showRepository.findById(showId);
     if (show.isPresent()) {
       return show.get();
     }
     return null;
  }

  @Override
  public byte[] setPosterForShow(Long showId, String contentType, byte[] poster) {
    ShowModel show = getShowById(showId);
    if (show != null) {
      show.setPoster(poster);
      ShowModel result = showRepository.save(show);
      if (result != null) {
        return result.getPoster();
      }
    }
    return null;
  }
}
