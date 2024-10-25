package media.libary.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import media.libary.dao.ActorDao;
import media.libary.dao.EpisodeDao;
import media.libary.dao.ShowDao;
import media.libary.entity.Show;
import media.libary.entity.ShowInput;

@Service
public class MediaService {
  //@Autowired
  private ActorDao actorDao;
  private EpisodeDao episodeDao;
  private ShowDao showDao;
  
  public MediaService(ActorDao actorDao, EpisodeDao episodeDao, ShowDao showDao) {
    this.actorDao = actorDao;
    this.episodeDao = episodeDao;
    this.showDao = showDao;
  }
  
  public List<Show> allShows() {
    List<Show> shows = showDao.findAll();
    return shows;
  }
  
  public Show getShow(Long id) {
    if (id <= 0) {
      return null;
    }
    
    Optional<Show> show = showDao.findById(id);
    //if (show.isPresent()) {
    //  return show.get();
    //}
    //return null;
    return show.orElse(null);
  }
  
  public Show createShow(ShowInput input) {
    if (input == null) {
      return null;
    }
    
    Show show = new Show();
    show.setName(input.getName());
    show.setDescription(input.getDescription());
    Show created = showDao.save(show);
    return created;
  }
}
