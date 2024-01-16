package media.libary.controller.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateActorInput extends Input {
  private Date birthdate;
  private String lastname;
  private String firstname;
  private String description;

  public CreateActorInput(CreateActorInput parent) {
    if (parent != null) {
      setBirthdate(parent.getBirthdate());
      setLastname(parent.getLastname());
      setFirstname(parent.getFirstname());
      setBirthdate(parent.getBirthdate());
      setDescription(parent.getDescription());
    }
  }
}
