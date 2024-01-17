package media.libary.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import media.libary.repository.ActorRepository;
import media.libary.repository.EpisodeRepository;
import media.libary.repository.ShowRepository;
import media.libary.repository.model.ShowModel;
import media.libary.service.model.CreateShowInput;
import media.libary.service.model.Show;

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
  
  private Show toShow(ShowModel model) {
    Show result = new Show();
    result.setId(model.getShow_id());
    result.setName(model.getShow_name());
    result.setDescription(model.getShow_description());
    result.setRating(model.getShow_rating());
    return result;
  }
  
  public Response<Show> createShow(CreateShowInput input) {
    ShowModel model = toShowModel(input);
    if (model != null) {
      ShowModel result = showRepository.save(model);
      return Response.OK(toShow(result));
    }
    return Response.Error(500, "Failed to save due to an unhandled data access layer error.");
  }
  
  /**
   * Retrieves all of the available shows.
   * @return The collection of shows.
   */
  public List<Show> getAllShows() {
    List<ShowModel> shows = showRepository.findAll();
    //List<Show> result = new ArrayList<Show>();
    //for(ShowModel model: shows) {
    //  Show show = toShow(model);
    //  if (show != null) {
    //    result.add(show);
    //  }
    //}
    // return result;
    
    return shows.stream().map(m -> {
      return toShow(m);
    }).toList();
  }

  /**
   * Applies a rating to a show.
   * @param showId The unique show id.
   * @param rating A value from 0 - 5, with 0 being the lowest.
   * @param comment An optional comment associated with the rating.
   * @return A response indicating the results of the operation.
   */
  public Response<Show> rateShow(Long showId, Double rating, String comment) {
    Optional<ShowModel> existing = this.showRepository.findById(showId);
    if (existing.isPresent()) {
      ShowModel model = existing.get();
      model.setShow_rating(rating);
      model.setShow_comment(comment);

      ShowModel result = this.showRepository.save(model);
      if (result != null) {
        return Response.OK(toShow(result));
      }
      return Response.InternalServerError("Apply ratings to show failed due to an unhandled error.");
    }
    return Response.NotFound("Requested show was not found.");
  }

  public List<Show> getAllShowsWithRating(Double rating_min, Double rating_max) {
    List<ShowModel> shows = this.showRepository.getShowsByRating(rating_min, rating_max);
    return shows.stream().map((show) -> {
      return toShow(show);
    }).toList();
  }
}
