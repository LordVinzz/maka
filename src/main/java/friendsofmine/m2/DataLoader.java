package friendsofmine.m2;

import friendsofmine.m2.domain.Activite;
import friendsofmine.m2.domain.Utilisateur;
import friendsofmine.m2.services.ActiviteService;
import friendsofmine.m2.services.UtilisateurService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final UtilisateurService utilisateurService;
    private final ActiviteService activiteService;

    @Getter
    private final Utilisateur thom = new Utilisateur("Yorke", "Thom", "thom@rh.com", "M");
    @Getter
    private final Utilisateur ed = new Utilisateur("Obrien", "Ed", "ed@rh.com", "M");
    @Getter
    private final Utilisateur karen = new Utilisateur("Orzolek", "Karen", "karen@yyy.com", "F");
    @Getter
    private final Utilisateur julian = new Utilisateur("Casablancas", "Julian", "jc@ts.com", "M");

    @Getter
    private final Activite guitare = new Activite("Guitare", "Matériel non fourni", thom);
    @Getter
    private final Activite muscu = new Activite("Muscu", "Créneau réservé le mardi", ed);
    @Getter
    private final Activite poker = new Activite("Poker", "Petite blind à 1 euro", karen);
    @Getter
    private final Activite pingpong = new Activite("Ping Pong", "Matériel non fourni", julian);
    @Getter
    private final Activite jogging = new Activite("Jogging", "Tous les midis", ed);
    @Getter
    private final Activite philo = new Activite("Philo", "Le club des admirateurs de Socrate", thom);
    @Getter
    private final Activite procrastination = new Activite("Procrastination", "On verra demain", thom);

    @Autowired
    public DataLoader(ActiviteService activiteService, UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
        this.activiteService = activiteService;
    }

    public void initUtilisateurs() {
        // Les utilisateurs sont déjà instanciés via les attributs de la classe.
        // Cette méthode permet de conserver la sémantique historique qui
        // sépare la préparation des données de leur persistance effective.
    }

    public void initActivites() {
        thom.addActivite(guitare);
        ed.addActivite(muscu);
        karen.addActivite(poker);
        julian.addActivite(pingpong);
        ed.addActivite(jogging);
        thom.addActivite(philo);
        thom.addActivite(procrastination);
    }

    @Override
    public void run(ApplicationArguments args) {

        initUtilisateurs();
        initActivites();
        saveUtilisateursAndActivites();
    }

    public void saveUtilisateursAndActivites() {
        utilisateurService.saveUtilisateur(thom);
        utilisateurService.saveUtilisateur(ed);
        utilisateurService.saveUtilisateur(karen);
        utilisateurService.saveUtilisateur(julian);
    }
}
