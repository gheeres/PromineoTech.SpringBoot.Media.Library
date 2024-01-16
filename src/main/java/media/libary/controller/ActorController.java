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
import media.libary.controller.model.ActorSearchInput;
import media.libary.controller.model.CreateActorInput;
import media.libary.controller.model.Image;
import media.libary.controller.model.ModifyActorInput;
import media.libary.controller.model.Show;
import media.libary.exception.DeleteException;
import media.libary.exception.SaveException;
import media.libary.service.MediaService;

@RestController
@RequestMapping("/actors")
@Slf4j
@Tag(name = "Actors", description = "Available actors.")
public class ActorController {
  // @Autowired
  private MediaService service;
  
  public ActorController(MediaService service) {
    this.service = service;
  }
  
  @GetMapping(value = "")
  public List<Actor> all() {
    log.info("Receieved request to get all actors");

    return service.getAllActors();
  }
  
  @GetMapping(value = "/search")
  public List<Actor> search(ActorSearchInput input) {
    log.info("Receieved request to search for actors {}", input);

    return service.findActors(input);
  }

  @GetMapping(value = "{actorId}")
  public Actor getActorById(@PathVariable Long actorId) {
    log.info("Receieved request to get actor {}", actorId);

    return service.getActor(actorId);
  }

  @PostMapping("")
  @ResponseStatus(value = HttpStatus.CREATED)
  public Actor createActor(@RequestBody CreateActorInput input) {
    log.info("Receieved request to create new actor {}", input);
    
    Actor result = service.createActor(input);
    if (result != null) {
      return result;
    }
    throw new SaveException(input, "Failed to create requested actor.");
  }
  
  @PutMapping("{actorId}")
  public Actor updateActor(@PathVariable Long actorId, @RequestBody ModifyActorInput input) {
    log.info("Receieved request to modify / update existing actor ({}). Changes: {}", actorId, input);
    
    Actor result = service.modifyActor(actorId, input);
    if (result != null) {
      return result;
    }
    throw new SaveException(input, String.format("Failed to modify actor (%s)", actorId));
  }

  @DeleteMapping("{actorId}")
  @ResponseStatus(value = HttpStatus.ACCEPTED)
  public Actor deleteActor(@PathVariable Long actorId) {
    log.info("Receieved request to delete actor ({})", actorId);
    
    Actor result = service.deleteActor(actorId);
    if (result != null) {
      return result;
    }
    throw new DeleteException(actorId, String.format("Failed to remove actor (%s)"));
  }
  
  private ResponseEntity<byte[]> getPhoto(Long actorId, String filename, Boolean download)
  {
    Image poster = service.getDefaultPhoto(actorId);
    if (poster != null) {
      HttpHeaders headers = new HttpHeaders();
      headers.add("Content-Type", poster.getContentType());
      if (download) {
        headers.add("Content-Disposition", "attachment; filename=\"Actor-%010d.jpg\"".formatted(actorId));
      }
      return ResponseEntity.status(HttpStatus.OK)
                           .headers(headers)
                           .body(poster.getData());
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                         .body((byte[]) null);
  }
  
  @RequestMapping(value = "{actorId}/photo/current/download", produces = "image/*", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<byte[]> downloadDefaultPoster(@PathVariable Long actorId, @RequestParam(required = false) String filename) {
    log.info("Receieved request for default photo for actor ({})", actorId);
    
    return getPhoto(actorId, filename, true);
  }

  @RequestMapping(value = "{actorId}/photo/current/raw", produces = "image/*", method = RequestMethod.GET)
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<byte[]> getDefaultPoster(@PathVariable Long actorId) {
    log.info("Receieved request for default photo for actor ({})", actorId);
    
    return getPhoto(actorId, null, false);
  }
  
  @PutMapping("{actorId}/photo/current")
  public ResponseEntity<Map<String,String>> setDefaultPhoto(@PathVariable Long actorId, @RequestParam("file") MultipartFile file) {
    log.info("Receieved request to modify photo for actor ({}). File: {}/{} ({})", actorId, 
             file.getContentType(), file.getSize(), file.getOriginalFilename());
    
    Map<String,String> response = new HashMap<>();
    try {
      Image image = new Image();
      image.setData(file.getBytes());
      image.setContentType(file.getContentType());

      var result = service.setDefaultActorPhoto(actorId, image);
      if (result != null) {
        response.put("size", result.getSize().toString());
        response.put("contentType", result.getContentType());
        return ResponseEntity.ok(response);
      }
    } catch (IOException e) {
      response.put("exception", e.getMessage());
    }
    
    response.put("error", "Failed to set requested actor photo due to an unhandle operation.");
    return ResponseEntity.internalServerError()
                         .body(response);
  }
}
