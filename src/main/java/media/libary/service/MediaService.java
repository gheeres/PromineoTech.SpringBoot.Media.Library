package media.libary.service;

import java.util.List;
import org.springframework.stereotype.Service;
import media.libary.controller.model.Actor;
import media.libary.controller.model.ActorSearchInput;
import media.libary.controller.model.CreateActorInput;
import media.libary.controller.model.CreateShowInput;
import media.libary.controller.model.Episode;
import media.libary.controller.model.Image;
import media.libary.controller.model.ModifyActorInput;
import media.libary.controller.model.ModifyShowInput;
import media.libary.controller.model.Show;
import media.libary.controller.model.ShowSearchInput;
import media.libary.exception.ActorNotFoundException;
import media.libary.exception.EpisodeNotFoundException;
import media.libary.exception.InvalidInputException;
import media.libary.exception.SaveException;
import media.libary.exception.ShowNotFoundException;
import media.libary.exception.ShowPosterUploadException;
import media.libary.repository.ActorRepository;
import media.libary.repository.EpisodeRepository;
import media.libary.repository.ShowRepository;
import media.libary.repository.model.ActorModel;
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

  public Image getDefaultPoster(Long showId) {
    ShowModel model = showRepository.findById(showId).orElseThrow(() -> {
      throw new ShowNotFoundException(showId);
    });
    return Convert.toImage(model);
  }
  
  public Image setDefaultShowPoster(Long showId, Image image) {
    ShowModel model = showRepository.findById(showId).orElseThrow(() -> {
      throw new ShowNotFoundException(showId);
    });

    model.setPoster(image.getData());
    ShowModel result = showRepository.save(model);
    if (result != null) {
      return Convert.toImage(result);
    }
    throw new ShowPosterUploadException(showId, image, String.format("Failed to modify show poster for %s (%d). File: %d (%s)",
                                                                     model.getShowName(), model.getShowId(), 
                                                                     image.getSize(), image.getContentType()));
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
    
    List<EpisodeModel> episodes = episodeRepository.findAllByShowShowId(show.getShowId());
    return episodes.stream().map(e -> {
      return Convert.toEpisode(e);
    }).toList();
  }

  public List<Episode> getEpisodesForSeason(Long showId, Long season) {
    ShowModel show = showRepository.findById(showId).orElseThrow(() -> {
      throw new ShowNotFoundException(showId);
    });

    List<EpisodeModel> episodes = episodeRepository.findAllByShowShowIdAndEpisodeSeason(show.getShowId(), season);
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

    EpisodeModel episode = episodeRepository.findByShowShowIdAndEpisodeSeasonAndEpisodeEpisode(show.getShowId(), season, episodeNumber);
    return Convert.toEpisode(episode);
  }

  public List<Episode> getAllEpisodesForShowBySeason(Long showId, Long season) {
    ShowModel show = showRepository.findById(showId).orElseThrow(() -> {
      throw new ShowNotFoundException(showId);
    });

    List<EpisodeModel> episodes = episodeRepository.findAllByShowShowIdAndEpisodeSeason(show.getShowId(), season);
    return episodes.stream().map(e -> {
      return Convert.toEpisode(e);
    }).toList();
  }
  
  public List<Actor> getAllActors() {
    List<ActorModel> models = actorRepository.findAll();
    return models.stream().map(m -> {
      return Convert.toActor(m);
    }).toList();
  }
  
  public List<Actor> getAllActorsForShow(Long showId) {
    ShowModel show = showRepository.findById(showId).orElseThrow(() -> {
      throw new ShowNotFoundException(showId);
    });
    
    List<ActorModel> actors = actorRepository.findAllByShowId(show.getShowId());
    return actors.stream().map(e -> {
      return Convert.toActor(e, false);
    }).toList();
  }

  public List<Actor> findActors(ActorSearchInput input) {
    if (input == null) {
      return getAllActors();
    }

    List<ActorModel> models = actorRepository.findByName(input.getName());
    return models.stream().map(m -> {
      return Convert.toActor(m);
    }).toList();
  }

  /**
   * Creates a new actor.
   * @param input The actor
   * @return The actor if created, otherwise returns null.
   */
  public Actor createActor(CreateActorInput input) {
    ActorModel model = Convert.toActorModel(input);
    if (model != null) {
      ActorModel result = actorRepository.save(model);
      if (result != null) {
        return Convert.toActor(result);
      }
      throw new SaveException(model);
    }
    throw new InvalidInputException(input, "No input received or required values missing.");
  }

  public Actor getActor(Long actorId) {
    ActorModel model = actorRepository.findById(actorId).orElseThrow(() -> {
      throw new ActorNotFoundException(actorId);
    });
    return Convert.toActor(model);
  }

  public Image getDefaultPhoto(Long actorId) {
    ActorModel model = actorRepository.findById(actorId).orElseThrow(() -> {
      throw new ActorNotFoundException(actorId);
    });
    return Convert.toImage(model);
  }
  
  public Image setDefaultActorPhoto(Long actorId, Image image) {
    ActorModel model = actorRepository.findById(actorId).orElseThrow(() -> {
      throw new ActorNotFoundException(actorId);
    });

    model.setPhoto(image.getData());
    ActorModel result = actorRepository.save(model);
    if (result != null) {
      return Convert.toImage(result);
    }
    throw new ShowPosterUploadException(actorId, image, String.format("Failed to modify actor photo for %s %s (%d). File: %d (%s)",
                                                                      model.getActorFirstname(), model.getActorLastname(), model.getActorId(), 
                                                                      image.getSize(), image.getContentType()));
  }

  public Actor modifyActor(Long actorId, ModifyActorInput input) {
    ActorModel existing = actorRepository.findById(actorId).orElseThrow(() -> {
      throw new ActorNotFoundException(actorId);
    });
    
    if (input != null) {
      // Only set or change the values that have been modified or specified.
      if (input.getFirstname().isPresent()) {
        existing.setActorFirstname(input.getFirstname().get());
      }
      if (input.getLastname().isPresent()) {
        existing.setActorLastname(input.getLastname().get());
      }
      if (input.getBirthdate().isPresent()) {
        existing.setActorBirthdate(input.getBirthdate().get());
      }
      if (input.getDescription().isPresent()) {
        existing.setActorDescription(input.getDescription().get());
      }
      
      ActorModel model = actorRepository.save(existing);
      if (model != null) {
        return Convert.toActor(model);
      }
      throw new SaveException(model, "Failed to modify requested actor information.");
    }
    throw new InvalidInputException(input, "No input received or required values missing for actor.");
  }

  public Actor deleteActor(Long actorId) {
    ActorModel existing = actorRepository.findById(actorId).orElseThrow(() -> {
      throw new ActorNotFoundException(actorId);
    });
    
    actorRepository.delete(existing);
    return Convert.toActor(existing);
  }
}