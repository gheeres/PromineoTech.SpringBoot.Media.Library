package media.libary.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import media.libary.entity.Show;

@Repository
public interface ShowDao extends JpaRepository<Show,Long>{
}
