package media.libary.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.v3.oas.annotations.tags.Tag;
import media.libary.repository.models.ShowModel;
import media.libary.service.MediaService;

@RestController
@Tag(name = "shows", description = "TV shows and other media.")
public class ShowController {
  private MediaService service;

  public ShowController(MediaService service)
  {
    this.service = service;
  }
  
  @GetMapping(value = "/shows")
  public List<ShowModel> all() {
    List<ShowModel> shows = service.getAllShows();
    if ((shows != null) && (shows.size() > 0)) {
      return shows;
    }
    
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No shows available.");
  }
  
  @GetMapping(value = "/shows/{showId}")
  public ShowModel get(@PathVariable Long showId) {
    ShowModel show = service.getShowById(showId);
    if (show != null) {
      return show;
    }
    
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Show not found.");
  }
  
  @PutMapping(value = "/shows/{showId}/poster")
  public Map<String,String> setPoster(@PathVariable Long showId, @RequestParam(name="file") MultipartFile file) {
    Map<String,String> response = new HashMap<>();
    
    byte[] poster;
    try {
      poster = service.setPosterForShow(showId, file.getContentType(), file.getBytes());
      if ((poster != null) && (poster.length > 0)) {
        response.put("size", Long.toString(poster.length));
        response.put("contentType", file.getContentType());
        
        return response;
      }
      response.put("error", "Failed to set post for show");
    } catch (IOException e) {
      response.put("error", e.getMessage());
    }
    
    return response;
  }
  
  @GetMapping(value = "/shows/{showId}/poster")
  public ResponseEntity<byte[]> getPoster(@PathVariable Long showId) {
    ShowModel show = service.getShowById(showId);
    if (show != null) {
      byte[] poster = show.getPoster();
      if ((poster != null) && (poster.length > 0)) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "image/jpg");
        return ResponseEntity.status(HttpStatus.OK)
                             .headers(headers)
                             .body(poster);
      }
      
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
                           .body((byte[]) null);
    }

    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Show not found.");
  }
}
