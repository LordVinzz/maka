package friendsofmine.m2.services;

import friendsofmine.m2.domain.Activite;
import friendsofmine.m2.domain.Utilisateur;
import friendsofmine.m2.repositories.ActiviteRepository;
import friendsofmine.m2.repositories.UtilisateurRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Service
@NoArgsConstructor
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private ActiviteRepository activiteRepository;

    public Utilisateur findUtilisateurById(Long l) {
        return utilisateurRepository.findById(l).orElse(null);
    }

    public Utilisateur saveUtilisateur(Utilisateur util) {
        if (util == null) throw new IllegalArgumentException("Entity must not be null");
        return utilisateurRepository.save(util);
    }


    public Long countUtilisateur() {
        return utilisateurRepository.count();
    }

    @Transactional
    public void deleteUtilisateur(Utilisateur utilisateur) {
        if (utilisateur == null) {
            throw new IllegalArgumentException("Entity must not be null");
        }

        Long utilisateurId = utilisateur.getId();
        if (utilisateurId == null) {
            throw new IllegalArgumentException("Entity must have an identifier");
        }

        Utilisateur managed = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new IllegalArgumentException("Unknown utilisateur with id=" + utilisateurId));

        List<Activite> activites = new ArrayList<>(managed.getActivites());
        for (Activite activite : activites) {
            activite.setResponsable(null);
            activiteRepository.save(activite);
        }

        if (managed != utilisateur) {
            for (Activite activite : new ArrayList<>(utilisateur.getActivites())) {
                activite.setResponsable(null);
            }
        }

        utilisateurRepository.delete(managed);
    }
}
