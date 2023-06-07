package media.libary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import media.libary.repository.model.ActorModel;

@Repository
public interface ActorRepository extends JpaRepository<ActorModel,Long> {
}
