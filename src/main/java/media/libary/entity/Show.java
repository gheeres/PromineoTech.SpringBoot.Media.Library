package media.libary.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
;
@Data
@jakarta.persistence.Entity
@Table(name = "\"show\"")
@ToString(exclude = "episodes")
@EqualsAndHashCode(callSuper = false, exclude = "episodes")
public class Show extends Entity {
  @Id
  @Column(name = "show_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  
  @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
  private Set<Episode> episodes = new HashSet<Episode>();
  
  public Show() {
    //episodes = new HashSet<Episode>();
  }
  
  public Show(Show parent) {
    if (parent != null) {
      setId(parent.getId());
      setName(parent.getName());
      setDescription(parent.getDescription());
      setEpisodes(parent.getEpisodes());
    }
  }
}
