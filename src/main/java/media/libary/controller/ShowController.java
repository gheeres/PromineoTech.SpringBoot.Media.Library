package media.libary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import media.libary.service.MediaService;
import media.libary.service.Response;
import media.libary.service.models.CreateShowInput;
import media.libary.service.models.Show;

@Slf4j
@RestController
@Tag(name="Shows", description = "Available television shows & episodes.")
public class ShowController {
  // @Autowired
  private MediaService service;
  
  public ShowController(MediaService service) {
    this.service = service;
  }
  
  @PostMapping("/shows")
  public Show create(@RequestBody CreateShowInput input) {
    log.info("Attempting to create show. Input: {}", input.toJson());
    
    if (! input.isValid()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A required field is missing.");
    }
    
    Response<Show> response = service.createShow(input);
    if (response.isSuccessStatusCode()) {
      log.info("Created new requested show. Result: {}", response.getResult().toJson());
      return response.getResult();
    }
    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, response.getMessage());
  }
  
  @DeleteMapping("/shows/{showId}")
  public Show delete(@PathVariable Long showId) {
    log.info("Attempting to remove show with unique id: {}", showId);
    
    Response<Show> response = service.deleteShow(showId);
    if (response.isSuccessStatusCode()) {
      log.info("Delete show. Show: {}", response.getResult().toJson());
      return response.getResult();
    }
    throw new ResponseStatusException(HttpStatus.valueOf(response.getCode()), response.getMessage());
  }
}
