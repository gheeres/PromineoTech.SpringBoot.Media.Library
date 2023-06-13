package media.libary.service;

import java.util.List;
import org.springframework.stereotype.Service;
import media.libary.controller.model.CreateShowInput;
import media.libary.controller.model.Episode;
import media.libary.controller.model.ModifyShowInput;
import media.libary.controller.model.Show;
import media.libary.controller.model.ShowSearchInput;
import media.libary.exception.EpisodeNotFoundException;
import media.libary.exception.InvalidInputException;
import media.libary.exception.SaveException;
import media.libary.exception.ShowNotFoundException;
import media.libary.repository.ActorRepository;
import media.libary.repository.EpisodeRepository;
import media.libary.repository.ShowRepository;
import media.libary.repository.model.EpisodeModel;
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
  
  public List<Show> findShows(ShowSearchInput input) {
    if (input == null) {
      return getAllShows();
    }
    
    List<ShowModel> models = showRepository.findByName(input.getName());
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
    throw new InvalidInputException(input, "No input received or required values missing.");
  }

  public Show getShow(Long showId) {
    ShowModel model = showRepository.findById(showId).orElseThrow(() -> {
      throw new ShowNotFoundException(showId);
    });
    return Convert.toShow(model);
  }

  public Show modifyShow(Long showId, ModifyShowInput input) {
    ShowModel existing = showRepository.findById(showId).orElseThrow(() -> {
      throw new ShowNotFoundException(showId);
    });
    
    if (input != null) {
      // Only set or change the values that have been modified or specified.
      if (input.getName().isPresent()) {
        existing.setShowName(input.getName().get());
      }
      if (input.getDescription().isPresent()) {
        existing.setShowDescription(input.getDescription().get());
      }
      
      ShowModel model = showRepository.save(existing);
      if (model != null) {
        return Convert.toShow(model);
      }
      throw new SaveException(model, "Failed to modify requested show information.");
    }
    throw new InvalidInputException(input, "No input received or required values missing for show.");
  }

  public Show deleteShow(Long showId) {
    ShowModel existing = showRepository.findById(showId).orElseThrow(() -> {
      throw new ShowNotFoundException(showId);
    });
    
    showRepository.delete(existing);
    return Convert.toShow(existing);
  }

  public List<Episode> getAllEpisodesForShow(Long showId) {
    ShowModel show = showRepository.findById(showId).orElseThrow(() -> {
      throw new ShowNotFoundException(showId);
    });
    
    List<EpisodeModel> episodes = episodeRepository.findAllByShowShowId(showId);
    return episodes.stream().map(e -> {
      return Convert.toEpisode(e);
    }).toList();
  }

  public List<Episode> getEpisodesForSeason(Long showId, Long season) {
    ShowModel show = showRepository.findById(showId).orElseThrow(() -> {
      throw new ShowNotFoundException(showId);
    });

    List<EpisodeModel> episodes = episodeRepository.findAllByShowShowIdAndEpisodeSeason(showId, season);
    return episodes.stream().map(e -> {
      return Convert.toEpisode(e);
    }).toList();
  }

  public Episode getEpisodeForShowById(Long showId, Long episodeId) {
    ShowModel show = showRepository.findById(showId).orElseThrow(() -> {
      throw new ShowNotFoundException(showId);
    });
    
    EpisodeModel episode = episodeRepository.findById(episodeId).orElseThrow(() -> {
      throw new EpisodeNotFoundException(show, episodeId);
    });
    return Convert.toEpisode(episode);
  }

  public Episode getEpisodeForShowBySeasonEpisode(Long showId, Long season, Long episodeNumber) {
    ShowModel show = showRepository.findById(showId).orElseThrow(() -> {
      throw new ShowNotFoundException(showId);
    });

    EpisodeModel episode = episodeRepository.findByShowShowIdAndEpisodeSeasonAndEpisodeEpisode(showId, season, episodeNumber);
    return Convert.toEpisode(episode);
  }

  public List<Episode> getAllEpisodesForShowBySeason(Long showId, Long season) {
    ShowModel show = showRepository.findById(showId).orElseThrow(() -> {
      throw new ShowNotFoundException(showId);
    });

    List<EpisodeModel> episodes = episodeRepository.findAllByShowShowIdAndEpisodeSeason(showId, season);
    return episodes.stream().map(e -> {
      return Convert.toEpisode(e);
    }).toList();
  }
}