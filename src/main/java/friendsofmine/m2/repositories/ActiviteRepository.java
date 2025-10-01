package friendsofmine.m2.repositories;

import friendsofmine.m2.domain.Activite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface ActiviteRepository extends CrudRepository<Activite, Long> {
    @Override @NonNull @Query("select distinct act from Activite act join fetch act.responsable order by lower(act.titre)")
    List<Activite> findAll();

}
