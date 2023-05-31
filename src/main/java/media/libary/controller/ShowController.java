package media.libary.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import media.libary.controller.model.CreateShowInput;
import media.libary.controller.model.Show;
import media.libary.service.MediaService;
import media.libary.service.Response;

@RestController
@Slf4j
@RequestMapping("/shows")
@Tag(name = "Shows", description = "Available television shows & episodes.")
public class ShowController {
  private MediaService service;
  
  public ShowController(MediaService service) {
    this.service = service;
  }
  
  @PostMapping("")
  public Show create(@RequestBody CreateShowInput show) {
    log.info("Attempting to create Show. Input: {}", show.toJson());
    Response<Show> response = service.createShow(show);
    if (response.isSuccessStatusCode()) {
      return response.get();
    }
    
    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, response.getMessage());
  }
}
