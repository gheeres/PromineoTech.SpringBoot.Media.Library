package media.libary.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import media.libary.models.Image;
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

  public Image toImage(ShowModel model) {
    Image poster = new Image();
    poster.setData(model.getPoster());
    poster.setContentType(model.getPosterMediaType()); 
    return poster;
  }
  
  @Override
  public Image getPoster(Long id) {
    ShowModel model = getShow(id);
    if (model != null) {
      // Possibly resize photo, return empty placeholder if not found?
      return toImage(model);
    }
    return null;
  }

  @Override
  public Image setPoster(Long id, Image poster) {
    Optional<ShowModel> existing = showRepository.findById(id);
    if (existing.isPresent()) {
      ShowModel model = existing.get();
      model.setPosterMediaType(poster.getContentType());
      model.setPoster(poster.getData());
      ShowModel result = showRepository.save(model);
      if (result != null) {
        return toImage(result);
      }
    }
    return null;
  }
  
  @Override
  public boolean isValidShow(Long id) {
    return getShow(id) != null;
  }

}
