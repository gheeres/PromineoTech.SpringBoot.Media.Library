package media.libary.repository.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "actor")
public class ActorModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long actor_id;
  private Date actor_birthdate;
  private String actor_lastname;
  private String actor_firstname;
  private String actor_description;
}
