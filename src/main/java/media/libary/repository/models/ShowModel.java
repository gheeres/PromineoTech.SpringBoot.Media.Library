package media.libary.repository.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
}
