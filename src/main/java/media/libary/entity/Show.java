package media.libary.entity;

import lombok.Data;

@Data
public class Show extends Entity {
  private Long id;
  private String name;
  private String description;
  
  public Show() {
  }
  
  public Show(Show parent) {
    if (parent != null) {
      setId(parent.getId());
      setName(parent.getName());
      setDescription(parent.getDescription());
    }
  }
}
