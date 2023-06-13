package media.libary.controller.model;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyEpisodeInput extends Input {
  private Optional<Long> show = Optional.empty();
  private Optional<String> name = Optional.empty();
  private Optional<String> description = Optional.empty();

  public ModifyEpisodeInput(ModifyEpisodeInput parent) {
    if (parent != null) {
      this.show = parent.getShow();
      this.name = parent.getName();
      this.description = parent.getDescription();
    }
  }
  
  public void setShow(Long showId) {
    this.show = Optional.of(showId);
  }
  
  public void setName(String name) {
    this.name = Optional.of(name);
  }
  
  public void setDescription(String description) {
    this.description = Optional.of(description);
  }
}
