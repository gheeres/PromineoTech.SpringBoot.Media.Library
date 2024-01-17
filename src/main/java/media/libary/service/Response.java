package media.libary.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response<T> {
  private int code;
  private String message;
  private T result;
  
  public Response(int code, String message, T result) {
    this.code = code;
    this.message = message;
    this.result = result;
  }
  
  public static <T> Response<T> OK(T result) {
    return new Response<T>(200, null, result);
  }
  
  public static <T> Response<T> NotFound(String message) {
    return new Response<T>(404, message, null);
  }
  
  public static <T> Response<T> BadRequest(String message) {
    return new Response<T>(400, message, null);
  }

  public static <T> Response<T> Error(String message) {
    return new Response<T>(500, message, null);
  } 
  
  /**
   * Checks to see if the response is successful or not.
   * @return True if successful, false if otherwise.
   */
  public boolean isSuccessStatusCode() {
    return code >= 200 && code < 300;
  }
}
