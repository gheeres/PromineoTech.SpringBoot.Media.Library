package media.libary.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import media.libary.service.MediaService;
import media.libary.service.Response;
import media.libary.service.model.CreateShowInput;
import media.libary.service.model.Show;

@Slf4j
@RestController
@Tag(name = "Shows", description = "Information about television shows and episodes.")
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
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The show name is required.");
    }
    
    Response<Show> response = service.createShow(input);
    if (response.isSuccessStatusCode()) {
      log.info("Created new show. Show: {}", response.getResult().toJson());
      return response.getResult();
    }
    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, response.getMessage());
  }
  
  @GetMapping("/shows")
  public List<Show> all() {
    log.info("Retrieving all available shows...");
    
    List<Show> shows = service.getAllShows();
    return shows;
  }
}
