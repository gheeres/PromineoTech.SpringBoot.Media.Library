package media.libary.exception;

import lombok.Data;
import media.libary.repository.model.ShowModel;

@Data
public class EpisodeNotFoundException extends MediaLibraryException {
  private static final long serialVersionUID = 1L;
  private ShowModel show;
  private Long episodeId;
  
  public EpisodeNotFoundException(ShowModel show, Long episodeId) {
    this(show, episodeId, null, null);
  }

  public EpisodeNotFoundException(ShowModel show, Long episodeId, String message) {
    this(show, episodeId, message, null);
  }

  public EpisodeNotFoundException(ShowModel show, Long episodeId, Throwable cause) {
    this(show, episodeId, null, cause);
  }

  public EpisodeNotFoundException(ShowModel show, Long episodeId, String message, Throwable cause) {
    super((message == null) 
      ? String.format("Episode (%d) was not found for show. Show: %s", episodeId, (show != null) ? show.toJson() : "null") 
      : message, cause);
    setShow(show);
    setEpisodeId(episodeId);
  }
}
