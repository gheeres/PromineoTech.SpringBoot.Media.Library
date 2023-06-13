package media.libary.controller.model;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyShowInput extends Input {
  private Optional<String> name = Optional.empty();
  private Optional<String> description = Optional.empty();

  public ModifyShowInput(ModifyShowInput parent) {
    if (parent != null) {
      this.name = parent.getName();
      this.description = parent.getDescription();
    }
  }
  
  public void setName(String name) {
    this.name = Optional.of(name);
  }
  
  public void setDescription(String description) {
    this.description = Optional.of(description);
  }
}
