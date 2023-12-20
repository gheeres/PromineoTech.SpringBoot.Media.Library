package media.libary.service;

import org.springframework.stereotype.Service;
import media.libary.repository.ActorRepository;
import media.libary.repository.EpisodeRepository;
import media.libary.repository.ShowRepository;
import media.libary.repository.model.ShowModel;
import media.libary.service.model.CreateShowInput;

@Service
public class MediaService {
  private ShowRepository showRepository;
  private EpisodeRepository episodeRepository;
  private ActorRepository actorRepository;
  
  public MediaService(ShowRepository showRepository, EpisodeRepository episodeRepository,
                      ActorRepository actorRepository) {
    this.showRepository = showRepository;
    this.episodeRepository = episodeRepository;
    this.actorRepository = actorRepository;
  }
  
  private ShowModel toShowModel(CreateShowInput input) {
    ShowModel result = new ShowModel();
    result.setShow_name(input.getShow_name());
    result.setShow_description(input.getShow_description());
    return result;
  }
  
  Response<ShowModel> createShow(CreateShowInput input) {
    ShowModel model = toShowModel(input);
    if (model != null) {
      ShowModel result = showRepository.save(model);
      return Response.OK(result);
    }
    return Response.Error(500, "Failed to save due to an unhandled data access layer error.");
  }
}
