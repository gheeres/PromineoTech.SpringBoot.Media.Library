package media.libary.controller.model;

import lombok.Data;
import media.libary.Model;

@Data
public class Image extends Model {
  private String contentType;
  public Long getSize() {
    return (long) ((data != null) ? data.length : 0);
  };
  public byte[] data;
}
