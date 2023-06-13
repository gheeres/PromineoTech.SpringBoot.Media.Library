package media.libary.exception;

import lombok.Data;
import media.libary.Model;

@Data
public class DeleteException extends MediaLibraryException {
  private static final long serialVersionUID = 1L;
  
  private Object id;
  
  public DeleteException(Object id) {
    this(id, null, null);
  }

  public DeleteException(Object id, String message) {
    this(id, message, null);
  }

  public DeleteException(Object id, Throwable cause) {
    this(id, null, cause);
  }

  public DeleteException(Object id, String message, Throwable cause) {
    super(((message == null) 
        ? String.format("Failed to remove model due to an unhandled error. Id: %s", id) 
        : message), cause);
    setId(id);
  }
}
