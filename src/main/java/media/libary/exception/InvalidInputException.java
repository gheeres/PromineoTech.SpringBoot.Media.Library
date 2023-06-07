package media.libary.exception;

import lombok.Data;
import media.libary.controller.model.Input;

@Data
public class InvalidInputException extends MediaLibraryException {
  private Input input;
  
  public InvalidInputException(Input input) {
    this(input, null, null);
  }

  public InvalidInputException(Input input, String message) {
    this(input, message, null);
  }

  public InvalidInputException(Input input, Throwable cause) {
    this(input, null, cause);
  }

  public InvalidInputException(Input input, String message, Throwable cause) {
    super(((message == null) 
            ? String.format("Empty or invalid input for %s.", input) 
            : message), cause);
    setInput(input);
  }
}
