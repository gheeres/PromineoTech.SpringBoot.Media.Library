package media.libary.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import media.libary.entity.Show;
import media.libary.service.MediaService;

@RestController
public class ShowController {
  //AutoWired;
  private MediaService service;
  
  public ShowController(MediaService service) {
    this.service = service;
  }

  @GetMapping(value = "/shows")
  public List<Show> all() {
    List<Show> shows = service.allShows();
    return shows;
  }
}
