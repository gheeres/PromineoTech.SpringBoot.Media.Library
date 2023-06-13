package media.libary.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import media.libary.controller.model.CreateShowInput;
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
}
