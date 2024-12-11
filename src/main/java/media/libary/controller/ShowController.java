package media.libary.controller;

import org.springframework.web.bind.annotation.RestController;
import media.libary.service.MediaService;

@RestController
public class ShowController {
  private MediaService service;

  public ShowController(MediaService service)
  {
    this.service = service;
  }
}
