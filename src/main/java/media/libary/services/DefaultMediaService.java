package media.libary.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import media.libary.repository.ShowRepository;
import media.libary.repository.model.ShowModel;

@Service
@Slf4j
public class DefaultMediaService implements MediaService {
  private ShowRepository showRepository;
  
  public DefaultMediaService(ShowRepository showRepository) {
    this.showRepository = showRepository;
  }

  @Override
  public List<ShowModel> allShows() {
    log.debug("DefaultMediaService.allShows()");
    
    return showRepository.findAll();
  }

  @Override
  public ShowModel getShow(Long id) {
    log.debug("DefaultMediaService.getShow(id: {})", id);

    Optional<ShowModel> model = showRepository.findById(id);
    if (model.isPresent()) {
      return model.get();
    }
    return null;
  }
}
