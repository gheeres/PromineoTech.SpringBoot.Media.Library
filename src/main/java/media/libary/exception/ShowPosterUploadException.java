package media.libary.exception;

import lombok.Data;
import media.libary.controller.model.Image;

@Data
public class ShowPosterUploadException extends ImageUploadException {
  private static final long serialVersionUID = 1L;
  
  private Long showId;
  
  public ShowPosterUploadException(Long showId, Image image) {
    this(showId, image, null, null);
  }

  public ShowPosterUploadException(Long showId, Image image, String message) {
    this(showId, image, message, null);
  }

  public ShowPosterUploadException(Long showId, Image image, Throwable cause) {
    this(showId, image, null, cause);
  }

  
  public ShowPosterUploadException(Long showId, Image image, String message, Throwable cause) {
    super(image, 
          ((message == null) 
          ? String.format("Failed to modify poster for requested show (%d). File: %d (%s)", 
                          showId,
                          (image != null) ? image.getSize(): 0,
                          (image != null) ? image.getContentType() : "n/a") 
          : message), cause);
    setShowId(showId);
  }
}
