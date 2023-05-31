package media.libary.controller.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Model implements JsonSerializable {
  /**
   * Helper method to seria1lize an input model to it's JSON representation.
   * @return The JSOn representation of the object.
   */
  public String toJson() {
    try {
      return new ObjectMapper().writeValueAsString(this);
    } catch (JsonProcessingException e) {
      return null;
    }
  }
}
