package media.libary.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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

@Data
@jakarta.persistence.Entity
@Table(name = "\"actor\"")
@ToString(exclude = "episodes")
@EqualsAndHashCode(callSuper = false, exclude = "episodes")
public class Actor extends Entity {
  @Id
  @Column(name = "actor_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "actor_birthdate")
  private Date birthdate;
  @Column(name = "actor_lastname")
  private String lastName;
  @Column(name = "actor_firstname")
  private String firstName;
  @Column(name = "actor_description")
  private String description;
  @Column(name = "photo_media_type")
  private String mediaType;
  @Lob
  @Column(columnDefinition = "LONGBLOB")
  private byte[] photo;
  
  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name ="episode_actor", joinColumns = @JoinColumn(name = "actor_id"),
             inverseJoinColumns = @JoinColumn(name ="episode_id"))
  private Set<Episode> episodes = new HashSet<Episode>();
  
  public Actor() {
  }
  
  public Actor(Actor parent) {
    if (parent != null) {
      setId(parent.getId());
      setBirthdate(parent.getBirthdate());
      setLastName(parent.getLastName());
      setFirstName(parent.getFirstName());
      setDescription(parent.getDescription());
      setEpisodes(parent.getEpisodes());
    }
  }
}
