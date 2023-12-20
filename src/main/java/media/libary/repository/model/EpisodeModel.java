package media.libary.repository.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="episode")
public class EpisodeModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long episode_id;
  
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name="show_id", nullable = false)
  private ShowModel show;
  
  private Long season_number;
  private Long episode_number;
  private String episode_name;
  private String episode_description;
}
