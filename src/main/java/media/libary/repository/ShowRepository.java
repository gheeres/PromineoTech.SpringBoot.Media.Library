package media.libary.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import media.libary.repository.model.ShowModel;

@Repository
public interface ShowRepository extends JpaRepository<ShowModel,Long> {
  @Query(value = "SELECT * FROM `show` WHERE show_rating >= ?1 AND show_rating <= ?2", nativeQuery = true)
  List<ShowModel> getShowsByRating(Double rating_min, Double rating_max);
}
