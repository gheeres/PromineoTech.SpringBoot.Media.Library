package media.libary.service;

import java.util.List;
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
}
