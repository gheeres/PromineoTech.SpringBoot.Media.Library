package media.libary.service.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Model {
  /**
   * Returns the JSON representation of the instance.
   * @return The JSON
   */
  public String toJson() {
    try {
      return new ObjectMapper().writeValueAsString(this);
    } catch (JsonProcessingException e) {
      return null;
    }
  }  
}
