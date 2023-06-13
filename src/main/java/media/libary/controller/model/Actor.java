package media.libary.controller.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.NoArgsConstructor;
import media.libary.Model;

@Data
@NoArgsConstructor
public class Actor extends Model {
  private Long id;
  private Date birthdate; 
  private String name;
  private String description;
  
  private List<Episode> episodes = new ArrayList<Episode>();

  public Actor(Actor parent) {
    if (parent != null) {
      setId(parent.getId());
      setBirthdate(parent.getBirthdate());
      setName(parent.getName());
      setDescription(parent.getDescription());
    }
  }
  /**
   * Retrieves the list of shows that the actor has appeared in.
   * @return The list of shows.
   */
  private List<Show> getShows() {
    return episodes.stream().map(e -> new Show(e.getShow()))
                            .distinct()
                            .collect(Collectors.toList());
  }
  
  @Override
  public String toString() {
    return String.format("[%s] %s", getId(), getName());
  }  
}
