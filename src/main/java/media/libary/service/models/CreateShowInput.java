package media.libary.service.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateShowInput extends Model {
  private String name;
  private String description;
  
  /**
   * Checks to see if the input is valid.
   * @return True if valid, false if otherwise.
   */
  public boolean isValid() {
    return (name != null) && (! name.isEmpty());
  }
}
