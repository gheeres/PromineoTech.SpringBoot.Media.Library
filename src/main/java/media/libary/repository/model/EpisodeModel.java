package media.libary.repository.model;

import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import media.libary.Model;

@Data
@Entity
@Table(name = "episode")
public class EpisodeModel extends Model {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long episodeId;
  
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "show_id", nullable = false)
  private ShowModel show;
  
  private Long episodeSeason;
  private Long episodeEpisode;
  private String episodeName;
  private String episodeDescription;

  @Fetch(FetchMode.SUBSELECT)
  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "episode_actor", joinColumns = @JoinColumn(name = "episode_id"),
      inverseJoinColumns = @JoinColumn(name = "actor_id"))
  private Set<ActorModel> actors = new HashSet<>();
}
