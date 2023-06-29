package media.libary.controller.model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Episode extends Model {
  private Long id;
  private Show show;
  private Long season;
  private Long episode;
  private String name;
  private String description;
  
  public List<Actor> actors = new ArrayList<Actor>();
}
