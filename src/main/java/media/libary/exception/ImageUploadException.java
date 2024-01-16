package media.libary.exception;

import lombok.Data;
import media.libary.controller.model.Image;

@Data
public class ImageUploadException extends MediaLibraryException {
  private static final long serialVersionUID = 1L;
  
  private Image image;
  
  public ImageUploadException(Image image) {
    this(image, null, null);
  }

  public ImageUploadException(Image image, String message) {
    this(image, message, null);
  }

  public ImageUploadException(Image image, Throwable cause) {
    this(image, null, cause);
  }

  
  public ImageUploadException(Image image, String message, Throwable cause) {
    super(((message == null) 
        ? String.format("Failed to process uploaded file due to an unhandled error. File: %d (%s)", 
                        (image != null) ? image.getContentType() : "n/a",
                        (image != null) ? image.getSize(): 0) 
        : message), cause);
    setImage(image);
  }
}
