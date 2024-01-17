package media.libary.service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShowRatingInput extends Model {
  private Double rating;
  private String comment;
  
  @Override
  public int hashCode() {
    return ((rating != null) ? rating.hashCode() : 0) ^
           ((comment != null) ? comment.hashCode() : 0) ;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof ShowRatingInput) {
      return (((ShowRatingInput) obj).getRating().equals(this.getRating()) &&
             ((ShowRatingInput) obj).getComment().equals(this.getComment()));
    }
    return false;
  }
}
