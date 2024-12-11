package media.libary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import media.libary.repository.models.ShowModel;

@Repository
public interface ShowRepository extends JpaRepository<ShowModel, Long>{
}
