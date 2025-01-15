package media.libary.repository.models;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
  
  @Lob
  @Basic(fetch = FetchType.LAZY)
  @Column(columnDefinition = "LONGBLOB")
  private byte[] poster;
}
