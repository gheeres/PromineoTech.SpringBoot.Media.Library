package media.libary.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Show extends Model {
  private Long id;
  private String name;
  private String description;
}
