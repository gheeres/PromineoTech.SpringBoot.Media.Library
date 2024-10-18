package media.libary.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import media.libary.entity.Episode;

@Repository
public interface EpisodeDao extends JpaRepository<Episode,Long> {
}
