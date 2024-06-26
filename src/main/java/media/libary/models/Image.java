package media.libary.models;

import lombok.Data;

@Data
public class Image {
  private String contentType;
  private byte[] data;
  
  public Long getSize() {
    return (long) ((data != null) ? data.length : 0);
  }
}
