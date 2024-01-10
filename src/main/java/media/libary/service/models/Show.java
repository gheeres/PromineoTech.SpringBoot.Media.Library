package media.libary.service.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Show extends Model {
  private Long id;
  private String name;
  private String description;

  @Override
  public String toString() {
    return String.format("[%s] %s", getId(), getName());  
  }
}
