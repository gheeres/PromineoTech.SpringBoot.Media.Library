package media.libary.exceptions;

public class NoDataException extends MediaLibraryException {

  public NoDataException(String message) {
    this(message, null);
  }
  
  public NoDataException(String message, Throwable exception) {
    super(message, exception);
  }
}
