package media.libary.exception;

import lombok.Data;
@Data
public class SaveException extends MediaLibraryException {
  private Object data;
  
  public SaveException(Object data) {
    this(data, null, null);
  }

  public SaveException(Object data, String message) {
    this(data, message, null);
  }

  public SaveException(Object data, Throwable cause) {
    this(data, null, cause);
  }

  public SaveException(Object data, String message, Throwable cause) {
    super(((message == null) 
            ? String.format("Save operation failed for %s.", data) 
            : message), cause);
    setData(data);
  }
}
