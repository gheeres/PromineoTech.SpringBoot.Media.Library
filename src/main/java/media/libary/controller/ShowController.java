package media.libary.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import media.libary.entity.Show;
import media.libary.entity.ShowInput;
import media.libary.service.MediaService;

@RestController
@Tag(name = "Shows", description = "Gets all of the available shows")
public class ShowController {
  //AutoWired;
  private MediaService service;
  
  public ShowController(MediaService service) {
    this.service = service;
  }

  @GetMapping(value = "/shows/")
  public List<Show> all() {
    List<Show> shows = service.allShows();
    return shows;
  }
  
  @GetMapping(value = "/shows/{id}")
  public Show get(@PathVariable Long id) {
    Show show = service.getShow(id);
    return show;
  }
  
  @PostMapping(value = "/shows/alt")
  public Show createAlternate(@RequestParam String name, @RequestParam String description) {
    return null;
  }
  
  @PostMapping(value = "/shows")
  public Show create(@RequestBody ShowInput input) {
    Show show = service.createShow(input);
    if (show != null) {
      return show;
    }
    return null;
  }
}
