package media.libary.exception;

import lombok.Data;

@Data
public class ActorNotFoundException extends MediaLibraryException {
  private static final long serialVersionUID = 1L;
  private Long actorId;
  
  public ActorNotFoundException(Long actorId) {
    this(actorId, null, null);
  }

  public ActorNotFoundException(Long actorId, String message) {
    this(actorId, message, null);
  }

  public ActorNotFoundException(Long actorId, Throwable cause) {
    this(actorId, null, cause);
  }

  public ActorNotFoundException(Long actorId, String message, Throwable cause) {
    super((message == null) ? String.format("Actor (%d) was not found.", actorId) : message, cause);
    setActorId(actorId);
  }
}
