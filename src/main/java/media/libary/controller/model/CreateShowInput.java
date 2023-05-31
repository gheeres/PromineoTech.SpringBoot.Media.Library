package media.libary.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateShowInput extends Input {
  private String name;
  private String description;

  public CreateShowInput(CreateShowInput parent) {
    if (parent != null) {
      setName(parent.getName());
      setDescription(parent.getDescription());
    }
  }
}
