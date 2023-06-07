package media.libary.exception;

import lombok.Data;

@Data
public class ShowNotFoundException extends MediaLibraryException {
  private static final long serialVersionUID = 1L;
  private Long showId;
  
  public ShowNotFoundException(Long showId) {
    this(showId, null, null);
  }

  public ShowNotFoundException(Long showId, String message) {
    this(showId, message, null);
  }

  public ShowNotFoundException(Long showId, Throwable cause) {
    this(showId, null, cause);
  }

  public ShowNotFoundException(Long showId, String message, Throwable cause) {
    super((message == null) ? String.format("Show (%d) was not found.", showId) : message, cause);
    setShowId(showId);
  }
}
