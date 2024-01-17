package media.libary.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import media.libary.repository.ShowRepository;
import media.libary.repository.model.ShowModel;
import media.libary.service.models.CreateShowInput;
import media.libary.service.models.ModifyShowInput;
import media.libary.service.models.Show;

@Service
public class MediaService {
  private ShowRepository showRepository;
  
  public MediaService(ShowRepository showRepository) {
    this.showRepository = showRepository;  
  }
  
  private ShowModel toShowModel(CreateShowInput input) {
    ShowModel model = new ShowModel();
    model.setShow_name(input.getName());
    model.setShow_description(input.getDescription());
    return model;
  }
  
  private Show toShow(ShowModel model) {
    Show result = new Show();
    result.setId(model.getShow_id());
    result.setName(model.getShow_name());
    result.setDescription(model.getShow_description());
    return result;
  }

  public Response<Show> createShow(CreateShowInput input) {
    ShowModel model = toShowModel(input);
    if (model != null) {
      ShowModel result = this.showRepository.save(model);
      return Response.OK(toShow(result));
    }
    return Response.Error("Invalid input");
  }
  
  public Response<Show> deleteShow(Long showId) {
    if (showId > 0) {
      Optional<ShowModel> existing = this.showRepository.findById(showId);
      if (existing.isPresent()) {
        ShowModel model = existing.get();
        this.showRepository.delete(model);
        return Response.OK(toShow(model));
      }
      return Response.NotFound("Show not found.");
    }
    return Response.Error("Invalid show id provided.");
  }
  
  public List<Show> getAllShows() {
    List<ShowModel> shows = this.showRepository.findAll();
    List<Show> result = new ArrayList<Show>();
    for(ShowModel show : shows) {
      Show model = toShow(show);
      if (model != null) {
        result.add(model);
      }
    }
    return result;
  }
  
  public Optional<Show> getShow(Long showId) {
    Optional<ShowModel> show = this.showRepository.findById(showId);
    if (show.isPresent() ) {
      return Optional.of(toShow(show.get()));
    }
    return Optional.empty();
  }

  public Response<Show> modifyShow(Long showId, ModifyShowInput input) {
    Optional<ShowModel> existing = this.showRepository.findById(showId);
    if (existing.isPresent()) {
      if (input != null) {
        ShowModel model = existing.get();
        if ((input.getName() != null) && 
            (! input.getName().equals(model.getShow_name()))) {
          model.setShow_name(input.getName());
        }
        if ((input.getDescription() != null) &&
            (! input.getDescription().equals(model.getShow_description())) ) {
          model.setShow_description(input.getDescription());
        }
        
        ShowModel result = this.showRepository.save(model);
        return Response.OK(toShow(result));
      }
      return Response.BadRequest("No changes specified or found.");
    }
    return Response.NotFound("Requested show not found.");
  }
}
