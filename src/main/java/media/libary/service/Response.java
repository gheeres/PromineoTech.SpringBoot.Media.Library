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
  
  public static <T> Response<T> Error(String message) {
    return new Response<T>(500, message, null);
  }  
}
