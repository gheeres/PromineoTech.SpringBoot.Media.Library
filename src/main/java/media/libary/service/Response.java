package media.libary.service;

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
  
}
