package media.libary.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import media.libary.repository.model.ActorModel;
import media.libary.repository.model.ShowModel;

@Repository
public interface ActorRepository extends JpaRepository<ActorModel,Long> {
  @Query(value = "SELECT * FROM actor WHERE CONCAT(actor_firstname,' ',actor_lastname) LIKE %?1%", nativeQuery = true)
  List<ActorModel> findByName(String name);
}
