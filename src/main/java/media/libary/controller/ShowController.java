package media.libary.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import media.libary.controller.model.Actor;
import media.libary.controller.model.CreateShowInput;
import media.libary.controller.model.Image;
import media.libary.controller.model.ModifyShowInput;
import media.libary.controller.model.Show;
import media.libary.controller.model.ShowSearchInput;
import media.libary.exception.DeleteException;
import media.libary.exception.SaveException;
import media.libary.service.MediaService;

@RestController
@RequestMapping("/shows")
@Slf4j
@Tag(name = "Shows", description = "Available television shows and episodes.")
public class ShowController {
  // @Autowired
  private MediaService service;
  
  public ShowController(MediaService service) {
    this.service = service;
  }
  
  @GetMapping(value = "")
  public List<Show> all() {
    log.info("Receieved request to get all shows");

    return service.getAllShows();
  }
  
  @GetMapping(value = "/search")
  public List<Show> search(ShowSearchInput input) {
    log.info("Receieved request to search for shows {}", input);

    return service.findShows(input);
  }

  @GetMapping(value = "{showId}")
  public Show getShowById(@PathVariable Long showId) {
    log.info("Receieved request to get show {}", showId);

    return service.getShow(showId);
  }

  @GetMapping(value = "{showId}/actors")
  public List<Actor> getActors(@PathVariable Long showId) {
    log.info("Receieved request to get actors for show {}", showId);

    return service.getAllActorsForShow(showId);
  }

  @PostMapping("")
  @ResponseStatus(value = HttpStatus.CREATED)
  public Show createShow(@RequestBody CreateShowInput input) {
    log.info("Receieved request to create new show {}", input);
    
    Show result = service.createShow(input);
    if (result != null) {
      return result;
    }
    throw new SaveException(input, "Failed to create requested show.");
  }
  
  @PutMapping("{showId}")
  public Show updateShow(@PathVariable Long showId, @RequestBody ModifyShowInput input) {
    log.info("Receieved request to modify / update existing show ({}). Changes: {}", showId, input);
    
    Show result = service.modifyShow(showId, input);
    if (result != null) {
      return result;
    }
    throw new SaveException(input, String.format("Failed to modify show (%s)", showId));
  }

  @DeleteMapping("{showId}")
  @ResponseStatus(value = HttpStatus.ACCEPTED)
  public Show deleteShow(@PathVariable Long showId) {
    log.info("Receieved request to delete show ({})", showId);
    
    Show result = service.deleteShow(showId);
    if (result != null) {
      return result;
    }
    throw new DeleteException(showId, String.format("Failed to remove show (%s)"));
  }
  
  private ResponseEntity<byte[]> getPoster(Long showId, String filename, Boolean download)
  {
    Image poster = service.getDefaultPoster(showId);
    if (poster != null) {
      HttpHeaders headers = new HttpHeaders();
      headers.add("Content-Type", poster.getContentType());
      if (download) {
        headers.add("Content-Disposition", "attachment; filename=\"Poster-%010d.jpg\"".formatted(showId));
      }
      return ResponseEntity.status(HttpStatus.OK)
                           .headers(headers)
                           .body(poster.getData());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                         .body((byte[]) null);
  }
  
  @RequestMapping(value = "{showId}/poster/download", produces = "image/*", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<byte[]> downloadDefaultPoster(@PathVariable Long showId, @RequestParam(required = false) String filename) {
    log.info("Receieved request for default poster for show ({})", showId);
    
    return getPoster(showId, filename, true);
  }

  @RequestMapping(value = "{showId}/poster/raw", produces = "image/*", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<byte[]> getDefaultPoster(@PathVariable Long showId) {
    log.info("Receieved request for default poster for show ({})", showId);
    
    return getPoster(showId, null, false);
  }
  
  @PutMapping("{showId}/poster")
  public ResponseEntity<Map<String,String>> setDefaultPoster(@PathVariable Long showId, @RequestParam("file") MultipartFile file) {
    log.info("Receieved request to modify poster for show ({}). File: {}/{} ({})", showId, 
             file.getContentType(), file.getSize(), file.getOriginalFilename());
    
    Map<String,String> response = new HashMap<>();
    try {
      Image image = new Image();
      image.setData(file.getBytes());
      image.setContentType(file.getContentType());

      var result = service.setDefaultShowPoster(showId, image);
      if (result != null) {
        response.put("size", result.getSize().toString());
        response.put("contentType", result.getContentType());
        return ResponseEntity.ok(response);
      }
    } catch (IOException e) {
      response.put("exception", e.getMessage());
    }
    
    response.put("error", "Failed to set requested poster due to an unhandle operation.");
    return ResponseEntity.internalServerError()
                         .body(response);
  }
}
