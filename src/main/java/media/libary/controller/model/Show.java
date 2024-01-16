package media.libary.controller.model;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;
import media.libary.Model;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_DEFAULT)
public class Show extends Model {
  private Long id;
  private String name;
  private String description;
  
  private Set<Episode> episodes;
  
  public Show(Show parent) {
    if (parent != null) {
      setId(parent.getId());
      setName(parent.getName());
      setDescription(parent.getDescription());
    }
  }
  
  @Override
  public String toString() {
    return String.format("[%s] %s",  getId(), getName());
  }
}
