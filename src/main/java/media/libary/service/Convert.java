package media.libary.service;

import media.libary.controller.model.Actor;
import media.libary.controller.model.CreateShowInput;
import media.libary.controller.model.Episode;
import media.libary.controller.model.Show;
import media.libary.repository.model.ActorModel;
import media.libary.repository.model.EpisodeModel;
import media.libary.repository.model.ShowModel;

public abstract class Convert {
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
  public static Actor toActor(ActorModel model) {
    if (model == null) return null;
    
    Actor actor = new Actor();
    actor.setId(model.getActorId());
    actor.setName(String.format("%s %s", model.getActorFirstname(), model.getActorLastname()));
    actor.setBirthdate(model.getActorBirthdate());
    actor.setDescription(model.getActorDescription());
    
    return actor;
  }
  
  public static Episode toEpisode(EpisodeModel model) {
    if (model == null) return null;
    
    Episode episode = new Episode();
    episode.setId(model.getEpisodeId());
    episode.setShow(Convert.toShow(model.getShow()));
    episode.setSeason(model.getEpisodeSeason());
    episode.setEpisode(model.getEpisodeEpisode());
    episode.setName(model.getEpisodeName());
    episode.setDescription(model.getEpisodeDescription());
    
    model.getActors().forEach((actor) -> {
      episode.getActors().add(Convert.toActor(actor));
    });
    
    return episode;
  }
}
