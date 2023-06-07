package media.libary.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateShowInput extends Input {
  private String name;
  private String description;
}
