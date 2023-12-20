package media.libary.service;

import org.springframework.stereotype.Service;
import media.libary.repository.ShowRepository;
import media.libary.repository.model.ShowModel;
import media.libary.service.models.CreateShowInput;

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
  Response<ShowModel> createShow(CreateShowInput input) {
    ShowModel model = toShowModel(input);
    if (model != null) {
      ShowModel result = this.showRepository.save(model);
      return Response.OK(result);
    }
    return Response.Error("Invalid input");
  }
}
