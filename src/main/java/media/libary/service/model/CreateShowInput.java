package media.libary.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateShowInput {
  private String show_name;
  private String show_description;
}
