package media.libary.service;

import media.libary.controller.model.CreateShowInput;
import media.libary.controller.model.Show;
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
}
