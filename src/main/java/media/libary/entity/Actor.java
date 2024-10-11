package media.libary.entity;

import java.util.Date;
import lombok.Data;

@Data
public class Actor extends Entity {
  private Long id;
  private Date birthdate;
  private String lastName;
  private String firstName;
  private String description;
  
  public Actor() {
  }
  
  public Actor(Actor parent) {
    if (parent != null) {
      setId(parent.getId());
      setBirthdate(parent.getBirthdate());
      setLastName(parent.getLastName());
      setFirstName(parent.getFirstName());
      setDescription(parent.getDescription());
    }
  }
}
