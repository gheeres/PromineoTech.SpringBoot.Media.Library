package media.libary.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorSearchInput extends Input {
  private String name;

  public ActorSearchInput(ActorSearchInput parent) {
    if (parent != null) {
      this.name = parent.getName();
    }
  }
}
