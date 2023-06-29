package media.libary.controller.error;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import lombok.extern.slf4j.Slf4j;
import media.libary.exception.ShowNotFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalControllerErrorHandler {

  @ExceptionHandler(ShowNotFoundException.class)
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  public Map<String,String> handleShowNotFoundException(ShowNotFoundException e, WebRequest webRequest) {
    String message = e.getMessage();
    String url = getRequestUri(webRequest);
    log.info("Url: {}; Message: {}",url, message);
    return Map.of(
      "message", message,
      "url", url
    );
  }
  
  public String getRequestUri(WebRequest webRequest) {
    if (webRequest instanceof ServletWebRequest swr) {
      return swr.getRequest().getRequestURI();
    }
    return null;
  }
}
