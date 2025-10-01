package friendsofmine.m2.services;

import friendsofmine.m2.domain.Activite;
import friendsofmine.m2.domain.Utilisateur;
import friendsofmine.m2.repositories.ActiviteRepository;
import friendsofmine.m2.repositories.UtilisateurRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Setter
@Getter
@Service
@RequiredArgsConstructor
public class ActiviteService {

    @Autowired
    private ActiviteRepository activiteRepository;
    @Autowired
    public UtilisateurRepository utilisateurRepository;

    @Transactional
    public Activite saveActivite(Activite act) {
        if (act == null) throw new IllegalArgumentException("Entity must not be null");

        if (act.getResponsable() != null) {
            Utilisateur responsable = utilisateurRepository.save(act.getResponsable());
            responsable.addActivite(act);
        }

        return activiteRepository.save(act);
    }

    public Activite findActiviteById(Long id) {
        return activiteRepository.findById(id).orElse(null);
    }


    public long countActivite() {
        return activiteRepository.count();
    }

    public ArrayList<Activite> findAllActivites() {
        return StreamSupport
                .stream(activiteRepository.findAll().spliterator(), false)
                .sorted((a1, a2) -> a1.getTitre().compareToIgnoreCase(a2.getTitre()))
                .collect(Collectors.toCollection(ArrayList::new));
    }



}

