package media.libary.exception;

public abstract class MediaLibraryException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  protected MediaLibraryException() {
    super();
  }

  protected MediaLibraryException(String message) {
    super(message);
  }
  
  protected MediaLibraryException(String message, Throwable cause) {
    super(message, cause);
  }
}
