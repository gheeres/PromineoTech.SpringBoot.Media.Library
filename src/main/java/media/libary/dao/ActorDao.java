package media.libary.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import media.libary.entity.Actor;

@Repository
public interface ActorDao extends JpaRepository<Actor,Long> {
}
