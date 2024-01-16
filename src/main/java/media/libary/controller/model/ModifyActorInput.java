package media.libary.controller.model;

import java.util.Date;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyActorInput extends Input {
  private Optional<Date> birthdate = Optional.empty();
  private Optional<String> lastname = Optional.empty();
  private Optional<String> firstname = Optional.empty();
  private Optional<String> description = Optional.empty();

  public ModifyActorInput(ModifyActorInput parent) {
    if (parent != null) {
      birthdate = parent.getBirthdate();
      lastname = parent.getLastname();
      firstname = parent.getFirstname();
      description = parent.getDescription();
    }
  }
  
  public void setLastname(String lastname) {
    this.lastname = Optional.of(lastname);
  }
  
  public void setFirstname(String firstname) {
    this.firstname = Optional.of(firstname);
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = Optional.of(birthdate);
  }

  public void setDescription(String description) {
    this.description = Optional.of(description);
  }
}
