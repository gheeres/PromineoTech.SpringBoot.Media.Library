package media.libary.service;

import media.libary.controller.model.Actor;
import media.libary.controller.model.CreateActorInput;
import media.libary.controller.model.CreateShowInput;
import media.libary.controller.model.Episode;
import media.libary.controller.model.Image;
import media.libary.controller.model.Show;
import media.libary.repository.model.ActorModel;
import media.libary.repository.model.EpisodeModel;
import media.libary.repository.model.ShowModel;

public abstract class Convert {
  /**
   * Converts an instance of ShowModel into an instance of Image
   * @param model The instance to convert
   * @return The converted instance.
   */
  public static Image toImage(ShowModel model) {
    if (model == null) return null;
    
    Image result = new Image();
    result.setContentType(model.getPosterMediaType());
    result.setData(model.getPoster());
    return result;
  }
  
  /**
   * Converts an instance of ShowModel into an instance of Image
   * @param model The instance to convert
   * @return The converted instance.
   */
  public static Image toImage(ActorModel model) {
    if (model == null) return null;
    
    Image result = new Image();
    result.setContentType(model.getPhotoMediaType());
    result.setData(model.getPhoto());
    return result;
  }  

  /**
   * Converts an instance of ShowModel into an instance of Show
   * @param model The instance to convert
   * @param includeEpisodes Indicates if the actors should be included as part of the serialization.
   * @return The converted instance.
   */
  public static Show toShow(ShowModel model, boolean includeEpisodes) {
    if (model == null) return null;
    
    Show result = new Show();
    result.setId(model.getShowId());
    result.setName(model.getShowName());
    result.setDescription(model.getShowDescription());
    if (includeEpisodes) {
      model.getEpisodes().forEach((episode) -> {
        result.getEpisodes().add(Convert.toEpisode(episode, false));
      });
    }
    return result;
  }

  /**
   * Converts an instance of ShowModel into an instance of Show
   * @param model The instance to convert
   * @return The converted instance.
   */
  public static Show toShow(ShowModel model) {
    if (model == null) return null;
    
    Show result = new Show();
    result.setId(model.getShowId());
    result.setName(model.getShowName());
    result.setDescription(model.getShowDescription());
    return result;
  }
  
  /**
   * Converts an instance of CreateShowInput into an instance of ShowModel
   * @param model The instance to convert
   * @return The converted instance.
   */  
  public static ShowModel toShowModel(CreateShowInput input) {
    if (input == null) return null;
    
    ShowModel model = new ShowModel();
    model.setShowName(input.getName());
    model.setShowDescription(input.getDescription());
    return model;
  }
  
  /**
   * Converts an instance of CreateActorInput into an instance of ActorModel
   * @param model The instance to convert
   * @return The converted instance.
   */  
  public static ActorModel toActorModel(CreateActorInput input) {
    if (input == null) return null;
    
    ActorModel model = new ActorModel();
    model.setActorBirthdate(input.getBirthdate());
    model.setActorLastname(input.getLastname());
    model.setActorFirstname(input.getFirstname());
    model.setActorDescription(input.getDescription());
    return model;
  }
  
  /**
   * Converts an instance of EpisodeModel into an instance of Episode
   * @param model The instance to convert
   * @return The converted instance.
   */
  public static Episode toEpisode(EpisodeModel model) {
    return toEpisode(model, true);
  }
  
  /**
   * Converts an instance of EpisodeModel into an instance of Episode
   * @param model The instance to convert
   * @param includeEpisodes Indicates if the actors should be included as part of the serialization.
   * @return The converted instance.
   */
  public static Episode toEpisode(EpisodeModel model, boolean includeActors) {
    if (model == null) return null;
    
    Episode result = new Episode();
    result.setId(model.getEpisodeId());
    result.setShow(Convert.toShow(model.getShow()));
    result.setSeason(model.getEpisodeSeason());
    result.setEpisode(model.getEpisodeEpisode());
    result.setName(model.getEpisodeName());
    result.setDescription(model.getEpisodeDescription());

    if (includeActors) {
      model.getActors().forEach((actor) -> {
        result.getActors().add(Convert.toActor(actor, false));
      });
    }
    return result;
  }

  /**
   * Converts an instance of ActorModel into an instance of Actor
   * @param model The instance to convert
   * @return The converted instance.
   */
  public static Actor toActor(ActorModel model) {
    return toActor(model, true);
  }
  
  /**
   * Converts an instance of ActorModel into an instance of Actor
   * @param model The instance to convert
   * @param includeEpisodes Indicates if the episodes should be included as part of the serialization.
   * @return The converted instance.
   */
  public static Actor toActor(ActorModel model, boolean includeEpisodes) {
    if (model == null) return null;
    
    Actor result = new Actor();
    result.setId(model.getActorId());
    result.setName(String.format("%s %s", model.getActorFirstname(), model.getActorLastname()));
    result.setBirthdate(model.getActorBirthdate());
    result.setDescription(model.getActorDescription());
    if (includeEpisodes) {
      model.getEpisodes().forEach((episode) -> {
        result.getEpisodes().add(Convert.toEpisode(episode));
      });
    }
    return result;
  }
}
