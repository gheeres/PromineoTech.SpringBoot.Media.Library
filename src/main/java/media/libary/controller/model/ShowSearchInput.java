package media.libary.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowSearchInput extends Input {
  private String name;

  public ShowSearchInput(ShowSearchInput parent) {
    if (parent != null) {
      this.name = parent.getName();
    }
  }
}
