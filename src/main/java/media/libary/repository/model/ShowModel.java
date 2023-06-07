package media.libary.repository.model;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "\"show\"")
public class ShowModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long showId;
  private String showName;
  private String showDescription;

  @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
  private Set<EpisodeModel> episodes = new HashSet<>();
}
