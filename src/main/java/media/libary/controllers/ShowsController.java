package media.libary.controllers;

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
import lombok.extern.slf4j.Slf4j;
import media.libary.exceptions.NoDataException;
import media.libary.models.Image;
import media.libary.repository.model.ShowModel;
import media.libary.services.MediaService;

@RestController
@Slf4j
@Tag(name = "Shows", description = "Available television shows and episodes.")
public class ShowsController {
  private MediaService service;
  
  public ShowsController(MediaService service) {
    this.service = service;
  }
  
  @GetMapping("/shows")
  public List<ShowModel> all() {
    log.debug("ShowsController.all()");
    
    List<ShowModel> shows = service.allShows();
    if (! shows.isEmpty()) {
      return shows;
    }
    
    log.info("No shows found...");
    throw new NoDataException("No shows found.");
    //throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No shows found.");
  }
  
  @GetMapping(value = "/shows/{id}")
  public ShowModel get(@PathVariable Long id) {
    ShowModel show = service.getShow(id);
    if (show != null) {
      return show;
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Request show (%d) was not found.", id));
  }
  
  @GetMapping(value = "/shows/{id}/poster")
  public ResponseEntity<byte[]> getPoster(@PathVariable Long id) {
    if (service.isValidShow(id)) {
      Image photo = service.getPoster(id);
      if (photo != null) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", photo.getContentType());
        return ResponseEntity.status(HttpStatus.OK)
                             .headers(headers)
                             .body(photo.getData());
      }
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No photo available for show (%d).", id));
    }
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Requested show was not found (%d).", id));
  }
  
  @PutMapping(value = "/shows/{id}/poster")
  public ResponseEntity<Map<String,String>> setPoster(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
    Image poster = new Image();
    poster.setContentType(file.getContentType());
    Map<String,String> response = new HashMap<>();
    try {
      poster.setData(file.getBytes());
      
      Image image = service.setPoster(id, poster);
      if (image != null) {
        response.put("size", image.getSize().toString());
        response.put("contentType", image.getContentType());
        
        return ResponseEntity.ok(response);
      }

      response.put("message", "Failed to save to database");
    } catch (IOException e) {
      response.put("exception", e.getMessage());
      e.printStackTrace();
    }
    
    return ResponseEntity.internalServerError()
                         .body(response);
  }
}
