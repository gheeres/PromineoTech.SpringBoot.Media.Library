package media.libary.exceptions;

public abstract class MediaLibraryException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  
  protected MediaLibraryException(String message) {
    this(message, null);
  }
  
  protected MediaLibraryException(String message, Throwable exception) {
    super(message, exception);
  }
}
