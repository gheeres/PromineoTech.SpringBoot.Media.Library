package media.libary.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@jakarta.persistence.Entity
@Table(name = "episode")
@ToString(exclude = "actors")
@EqualsAndHashCode(callSuper = false, exclude = "actors")
public class Episode extends Entity {
  @Id
  @Column(name = "episode_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long season;
  private Long number;
  private String name;
  private String description;
  
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "show_id", nullable = false)
  private Show show;
  
  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "episode_actor", joinColumns = @JoinColumn(name = "episode_id"),
             inverseJoinColumns = @JoinColumn(name = "actor_id"))
  private Set<Actor> actors = new HashSet<>();
  
  public Episode() {
  }
  
  public Episode(Episode parent) {
    if (parent != null) {
      setId(parent.getId());
      setSeason(parent.getSeason());
      setNumber(parent.getNumber());
      setName(parent.getName());
      setDescription(parent.getDescription());
      setShow(parent.getShow());
      setActors(parent.getActors());
    }
  }
}
