package media.libary.entity;

import lombok.Data;

@Data
public class Episode extends Entity {
  private Long id;
  private Long season;
  private Long number;
  private String name;
  private String description;
  
  public Episode() {
  }
  

  public Episode(Episode parent) {
    if (parent != null) {
      setId(parent.getId());
      setSeason(parent.getSeason());
      setNumber(parent.getNumber());
      setName(parent.getName());
      setDescription(parent.getDescription());
    }
  }
}
