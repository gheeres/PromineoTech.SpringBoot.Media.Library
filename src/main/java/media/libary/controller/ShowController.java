package media.libary.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import media.libary.controller.model.CreateShowInput;
import media.libary.controller.model.Show;
import media.libary.controller.model.ShowSearchInput;
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
  
  @PostMapping("")
  public Show createShow(@RequestBody CreateShowInput input) {
    log.info("Receieved request to create new show {}", input);
    
    Show result = service.createShow(input);
    if (result == null) {
      // throw error?
    }
    return result;
  }
  
  @GetMapping(value = "")
  public List<Show> all() {
    return service.getAllShows();
  }
  
  @GetMapping(value = "{showId}")
  public Show getShowById(@PathVariable Long showId) {
    //try {
      return service.getShow(showId);
    //} catch(ShowNotFoundException e) {
    //  throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    //}
  }
  
  @GetMapping(value = "search")
  public List<Show> search(ShowSearchInput input) {
    log.info("Received search request: {}", input);
    return service.findShows(input);
  }
}
