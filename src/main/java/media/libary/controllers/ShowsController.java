package media.libary.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import media.libary.repository.ShowRepository;
import media.libary.repository.model.ShowModel;

@RestController
@Slf4j
@Tag(name = "Shows", description = "Available television shows and episodes.")
public class ShowsController {
  private ShowRepository repository;
  
  public ShowsController(ShowRepository repository) {
    this.repository = repository;
  }
  
  @GetMapping("/shows")
  public List<ShowModel> all() {
    return repository.findAll();
  }
}
