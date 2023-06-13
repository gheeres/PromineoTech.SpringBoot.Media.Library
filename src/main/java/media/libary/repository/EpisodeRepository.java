package media.libary.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import media.libary.repository.model.EpisodeModel;

@Repository
public interface EpisodeRepository extends JpaRepository<EpisodeModel, Long> {
  EpisodeModel findByShowShowIdAndEpisodeSeasonAndEpisodeEpisode(Long showId, Long season, Long episode);
  List<EpisodeModel> findAllByShowShowId(Long showId);
  List<EpisodeModel> findAllByShowShowIdAndEpisodeSeason(Long showId, Long episodeSeason);
}
