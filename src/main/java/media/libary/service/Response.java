package media.libary.service;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response<T> {
  private int code;
  private String message;
  private T result;

  public Response(int code, T result, String message) {
    this.code = code;
    this.result = result;
    this.message = message;
  }
  
  public static <T> Response<T> OK(T result) {
    return new Response(200, result, null);
  }
  
  public static <T> Response<T> Error(int code, String message) {
    return new Response(code, null, message);
  }
  
  /**
   * Checks to see if the status code indicates a successful response.
   * @return True if successful, false if otherwise.
   */
  public boolean isSuccessStatusCode() {
    return (code >= 200 && code < 300);
  }
}
