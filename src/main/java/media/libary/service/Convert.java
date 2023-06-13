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
  
  /**
   * Converts an instance of EpisodeModel into an instance of Episode
   * @param model The instance to convert
   * @return The converted instance.
   */
  public static Episode toEpisode(EpisodeModel model) {
    if (model == null) return null;
    
    Episode result = new Episode();
    result.setId(model.getEpisodeId());
    result.setShow(Convert.toShow(model.getShow()));
    result.setSeason(model.getEpisodeSeason());
    result.setEpisode(model.getEpisodeEpisode());
    result.setName(model.getEpisodeName());
    result.setDescription(model.getEpisodeDescription());

    model.getActors().forEach((actor) -> {
      result.getActors().add(Convert.toActor(actor));
    });
    return result;
  }

  /**
   * Converts an instance of ActorModel into an instance of Actor
   * @param model The instance to convert
   * @return The converted instance.
   */
  public static Actor toActor(ActorModel model) {
    if (model == null) return null;
    
    Actor result = new Actor();
    result.setId(model.getActorId());
    result.setName(String.format("%s %s", model.getActorFirstname(), model.getActorLastname()));
    result.setBirthdate(model.getActorBirthdate());
    result.setDescription(model.getActorDescription());

    return result;
  }
}
