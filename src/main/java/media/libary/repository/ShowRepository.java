package media.libary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import media.libary.repository.model.ShowModel;

public interface ShowRepository extends JpaRepository<ShowModel,Long> {
}
