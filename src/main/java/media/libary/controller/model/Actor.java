package media.libary.controller.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;
import media.libary.Model;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_DEFAULT)
public class Actor extends Model {
  private Long id;
  private Date birthdate; 
  private String name;
  private String description;
  
  @JsonIgnore
  private List<Episode> episodes = new ArrayList<Episode>();

  public Actor(Actor parent) {
    if (parent != null) {
      setId(parent.getId());
      setBirthdate(parent.getBirthdate());
      setName(parent.getName());
      setDescription(parent.getDescription());
    }
  }
  
  @JsonIgnore
  public String getFirstname() {
    String[] nameComponents = getName().split(" (?!.* )");
    if (nameComponents.length > 0) {
      return nameComponents[0];
    }
    return null;
  }
  
  @JsonIgnore
  public String getLastname() {
    String[] nameComponents = getName().split(" (?!.* )");
    if (nameComponents.length > 1) {
      return nameComponents[1];
    }
    return null;
  }

  /**
   * Retrieves the list of shows that the actor has appeared in.
   * @return The list of shows.
   */
  public List<Show> getShows() {
    return episodes.stream().map(e -> new Show(e.getShow()))
                            .distinct()
                            .collect(Collectors.toList());
  }
  
  @Override
  public String toString() {
    return String.format("[%s] %s", getId(), getName());
  }  
}
