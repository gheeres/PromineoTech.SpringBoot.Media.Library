package media.libary.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Show extends Model {
  private Long id;
  private String name;
  private String description;
  
  public Show(Show parent) {
    if (parent != null) {
      setId(parent.getId());
      setName(parent.getName());
      setDescription(parent.getDescription());
    }
  }
  
  @Override
  public String toString() {
    return String.format("[%s] %s",  getId(), getName());
  }
}
