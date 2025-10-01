package friendsofmine.m2.repositories;

import friendsofmine.m2.domain.Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActiviteRepository extends JpaRepository<Activite, Long> {

    @Query("select distinct a from Activite a join fetch a.responsable order by lower(a.titre)")
    List<Activite> findAllWithResponsableOrderByTitre();
}
