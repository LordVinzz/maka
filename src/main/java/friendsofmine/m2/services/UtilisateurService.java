package friendsofmine.m2.services;

import friendsofmine.m2.domain.Activite;
import friendsofmine.m2.domain.Utilisateur;
import friendsofmine.m2.repositories.UtilisateurRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Setter
@Getter
@Service
@NoArgsConstructor
public class UtilisateurService {
    @Autowired
    private UtilisateurRepository utilisateurRepository;

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
}
