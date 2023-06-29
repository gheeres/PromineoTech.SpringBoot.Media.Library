package media.libary.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowSearchInput extends Input {
  private String name;
  private Integer year;
  
  public ShowSearchInput(ShowSearchInput input) {
    if (input != null) {
      this.name = input.name;
      this.year = input.year;
    }
  }
}
