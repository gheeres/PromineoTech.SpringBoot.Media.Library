package media.libary.controller.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Actor extends Model {
  private Long id;
  private Date birthdate;
  private String name;
  private String description;

  private List<Episode> episodes = new ArrayList<Episode>();
}
