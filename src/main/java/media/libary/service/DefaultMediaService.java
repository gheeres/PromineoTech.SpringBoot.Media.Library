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
  private EpisodeRepository episodeRepository;
  private ShowRepository showRepository;

  public DefaultMediaService(ActorRepository actorRepository, EpisodeRepository episodeRepository, ShowRepository showRepository) {
    this.actorRepository = actorRepository;
    this.episodeRepository = episodeRepository;
    this.showRepository = showRepository;
  }
  
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
  public List<ShowModel> searchShows(String name) {
    List<ShowModel> shows = showRepository.findByShowName(name);
    return shows;
  }

  @Override
  public byte[] setDefaultShowPoster(Long showId, byte[] image) {
    ShowModel show = getShowById(showId);
    if (show != null) {
      show.setPoster(image);
      ShowModel result = showRepository.save(show);
      if (result != null) {
        return result.getPoster();
      }
    }
    return null;
  }
}
