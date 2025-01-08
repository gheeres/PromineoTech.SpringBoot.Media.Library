package media.libary.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.v3.oas.annotations.tags.Tag;
import media.libary.repository.models.ShowModel;
import media.libary.service.MediaService;

@RestController
@RequestMapping("/shows")
@Tag(name = "Shows", description = "Gets all of the shows")
public class ShowController {
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
}
