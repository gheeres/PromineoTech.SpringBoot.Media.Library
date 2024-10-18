package media.libary.service;

import java.util.List;
import org.springframework.stereotype.Service;
import media.libary.dao.ActorDao;
import media.libary.dao.EpisodeDao;
import media.libary.dao.ShowDao;
import media.libary.entity.Show;

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
}
