package media.libary.controller.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import media.libary.Model;

@Data
@NoArgsConstructor
public class Episode extends Model {
  private Long id;
  private Show show;
  private Long season;
  private Long episode;
  private String name;
  private String description;
  
  private List<Actor> actors = new ArrayList<Actor>();
  
  public Episode(Episode parent) {
    if (parent != null) {
      setId(parent.getId());
      setShow(parent.getShow());
      setSeason(parent.getSeason());
      setEpisode(parent.getEpisode());
      setName(parent.getName());
      setDescription(parent.getDescription());
      
      for(Actor actor: actors) {
        actors.add(new Actor(actor));
      }
    }
  }
  
  @Override
  public String toString() {
    return String.format("S%2dE%2d - %s (%d)", getSeason(), getEpisode(),
                         getName(), getId());
  }  
}
