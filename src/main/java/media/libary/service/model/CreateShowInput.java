package media.libary.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateShowInput extends Model {
  private String show_name;
  private String show_description;
  
  public boolean isValid() {
    return (show_name != null) && (! show_name.isEmpty());
  }
}
