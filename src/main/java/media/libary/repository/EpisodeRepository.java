package media.libary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import media.libary.repository.model.EpisodeModel;

@Repository
public interface EpisodeRepository extends JpaRepository<EpisodeModel,Long> {
}
