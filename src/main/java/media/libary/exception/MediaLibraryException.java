package media.libary.exception;

import lombok.Data;

@Data
public abstract class MediaLibraryException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public MediaLibraryException(String message) 
  {
    this(message, null);
  }
  
  public MediaLibraryException(String message, Throwable cause) 
  {
    super(message, cause);
  }
}
