package media.libary.repository.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import media.libary.Model;

@Data
@Entity
@Table(name = "actor")
@ToString(exclude = "episodes")
@EqualsAndHashCode(callSuper=false, exclude = "episodes")
public class ActorModel extends Model {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long actorId;
  private Date actorBirthdate;
  private String actorLastname;
  private String actorFirstname;
  private String actorDescription;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "episode_actor", joinColumns = @JoinColumn(name = "actor_id"),
      inverseJoinColumns = @JoinColumn(name = "episode_id"))
  private Set<EpisodeModel> episodes = new HashSet<>();
  
  private String photoMediaType;
  
  @Lob
  @Basic(fetch = FetchType.LAZY)
  @Column(columnDefinition = "LONGBLOB")  
  private byte[] photo;
}
