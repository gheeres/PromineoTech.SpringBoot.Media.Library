package media.libary.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import media.libary.controller.model.Episode;
import media.libary.service.MediaService;

@RestController
@Slf4j
@Tag(name = "Episodes", description="Episodes for TV shows")
@RequestMapping(value = "/shows/{showId}/episodes")
public class EpisodeController {
  // @Autowired
  private MediaService service;
  
  public EpisodeController(MediaService service) {
    this.service = service;
  }
  
  @GetMapping(value = "")
  public List<Episode> all(@PathVariable Long showId) {
    log.info("Retrieving episodes for show ({}).", showId);
    
    return service.getAllEpisodesForShow(showId);
  }
  
}
