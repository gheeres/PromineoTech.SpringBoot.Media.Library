package media.libary.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import media.libary.repository.ShowRepository;
import media.libary.repository.model.ShowModel;

@RestController
@RequestMapping("/shows")
@Slf4j
@Tag(name = "Shows", description = "Available television shows and episodes.")
public class ShowController {
  // @Autowired
  private ShowRepository repository;
  
  public ShowController(ShowRepository repository) {
    this.repository = repository;
  }
  
  @GetMapping(value = "")
  public List<ShowModel> all() {
    return repository.findAll();
  }
}
