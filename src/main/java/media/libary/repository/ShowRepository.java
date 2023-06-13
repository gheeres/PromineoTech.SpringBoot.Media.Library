package media.libary.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import media.libary.repository.model.ShowModel;

@Repository
public interface ShowRepository extends JpaRepository<ShowModel,Long> {
  @Query(value = "SELECT * FROM `show` WHERE show_name LIKE %?1%", nativeQuery = true)
  List<ShowModel> findByName(String name);
}
