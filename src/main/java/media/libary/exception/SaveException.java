package media.libary.exception;

import lombok.Data;
import media.libary.Model;

@Data
public class SaveException extends MediaLibraryException {
  private static final long serialVersionUID = 1L;
  
  private Model model;
  
  public SaveException(Model model) {
    this(model, null, null);
  }

  public SaveException(Model model, String message) {
    this(model, message, null);
  }

  public SaveException(Model model, Throwable cause) {
    this(model, null, cause);
  }

  
  public SaveException(Model model, String message, Throwable cause) {
    super(((message == null) 
        ? String.format("Failed to save model due to an unhandled error. Model: %s", (model != null) ? model.toJson() : "null") 
        : message), cause);
    setModel(model);
  }
}
