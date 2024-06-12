package media.libary.controllers.error;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import lombok.extern.slf4j.Slf4j;
import media.libary.exceptions.NoDataException;

@Slf4j
@RestControllerAdvice
public class GlobalControllerErrorHandler {
  @ExceptionHandler(NoDataException.class)
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  public Map<String,String> handleNoDataException(NoDataException exception, WebRequest request) {
    String message = exception.getMessage();
    log.info("Url: {}; Message: {}", getRequestUri(request), message);
    return Map.of("error", message);
  }
  
  protected String getRequestUri(WebRequest request) {
    if (request instanceof ServletWebRequest swr) {
      return swr.getRequest().getRequestURI();
    }
    return null;
  }
}
