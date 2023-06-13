package media.libary.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import media.libary.controller.model.Episode;
import media.libary.service.MediaService;

@RestController
@RequestMapping("/shows/{showId}/episodes")
@Slf4j
@Tag(name = "Episodes", description = "Episodes for TV shows.")
public class EpisodeController {
  // @Autowired
  private MediaService service;
  
  public EpisodeController(MediaService service) {
    this.service = service;
  }
  
  @GetMapping(value = "")
  public List<Episode> all(@PathVariable Long showId, @RequestParam(required = false) Long season) {
    log.info("Retrieving episodes for show ({}); Season: {}", showId, season);
    
    if ((season != null) && (season > 0)) {
      return service.getEpisodesForSeason(showId, season);
    }
    return service.getAllEpisodesForShow(showId);
  }
  
  @GetMapping(value = "{season}")
  public List<Episode> allEpisodesForSeason(@PathVariable Long showId, @PathVariable Long season) {
    log.info("Retrieving all episodes for season (S{}) from show ({})", season, showId);

    return service.getAllEpisodesForShowBySeason(showId, season);
  }

  @GetMapping(value = "{season}/{episode}")
  public Episode getEpisodeById(@PathVariable Long showId, @PathVariable Long season, @PathVariable Long episode) {
    log.info("Retrieving episode (S{}E{}) from show ({})", season, episode, showId);

    return service.getEpisodeForShowBySeasonEpisode(showId, season, episode);
  }
}
