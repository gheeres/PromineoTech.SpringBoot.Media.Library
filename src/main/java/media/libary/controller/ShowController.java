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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.v3.oas.annotations.tags.Tag;
import media.libary.repository.models.ShowModel;
import media.libary.service.MediaService;

@RestController
@RequestMapping("/shows")
@Tag(name = "Shows", description = "Gets all of the shows")
public class ShowController {
  //@Autowired
  private MediaService service;
  
  public ShowController(MediaService service) {
    this.service = service;
  }
  
  @GetMapping(value = "")
  public List<ShowModel> all() {
    List<ShowModel> shows = service.getAllShows();
    return shows;
  }
  
  @GetMapping(value = "{showId}")
  public ShowModel getById(@PathVariable Long showId) {
    ShowModel show = service.getShowById(showId);
    if (show != null) {
      return show;
    }
    
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Show not found");
  }
  
  // Url: /shows/search?name=xyz
  @GetMapping(value = "search")  
  public List<ShowModel> search(@RequestParam String name) {
    if ((name == null) || (name.isEmpty())) { // name?.isEmpty()
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "name is required.");
    }
    
    List<ShowModel> shows = service.searchShows(name);
    return shows;
  }
  
  @GetMapping(value = "{showId}/poster")
  public ResponseEntity<byte[]> getPoster(@PathVariable Long showId) {
    ShowModel show = service.getShowById(showId);
    if (show != null) {
      byte[] poster = show.getPoster();
      if ((poster != null) && (poster.length > 0)) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "image/jpeg");
        return ResponseEntity.status(HttpStatus.OK)
                             .headers(headers)
                             .body(poster);
      }
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                         .body((byte[]) null);
  }
  
  @PutMapping(value = "{showId}/poster")
  public Map<String,String> setPoster(@PathVariable Long showId, @RequestParam("file") MultipartFile file) {
    Map<String,String> response = new HashMap<>();

    try {
      byte[] image = file.getBytes();
      byte[] savedImage = service.setDefaultShowPoster(showId, image);
      if (savedImage != null) {
        response.put("size", Long.toString(savedImage.length));
        response.put("contentType", file.getContentType());
        return response;
      }
      response.put("error", "Image not saved...");
    } catch (IOException e) {
      response.put("error", e.getMessage());
    }
    
    return response;
  }
}
