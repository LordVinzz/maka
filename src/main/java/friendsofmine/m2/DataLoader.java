package friendsofmine.m2;

import friendsofmine.m2.domain.Activite;
import friendsofmine.m2.domain.Utilisateur;
import friendsofmine.m2.services.ActiviteService;
import friendsofmine.m2.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final UtilisateurService utilisateurService;
    private final ActiviteService activiteService;

    private final Utilisateur thom = new Utilisateur("Yorke", "Thom", "thom@rh.com", "M");
    private final Utilisateur ed = new Utilisateur("Obrien", "Ed", "ed@rh.com", "M");
    private final Utilisateur karen = new Utilisateur("Orzolek", "Karen", "karen@yyy.com", "F");
    private final Utilisateur julian = new Utilisateur("Casablancas", "Julian", "jc@ts.com", "M");

    private final Activite guitare = new Activite("Guitare", "Matériel non fourni", thom);
    private final Activite muscu = new Activite("Muscu", "Créneau réservé le mardi", ed);
    private final Activite poker = new Activite("Poker", "Petite blind à 1 euro", karen);
    private final Activite pingpong = new Activite("Ping Pong", "Matériel non fourni", julian);
    private final Activite jogging = new Activite("Jogging", "Tous les midis", ed);
    private final Activite philo = new Activite("Philo", "Le club des admirateurs de Socrate", thom);
    private final Activite procrastination = new Activite("Procrastination", "On verra demain", thom);

    @Autowired
    public DataLoader(ActiviteService activiteService, UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
        this.activiteService = activiteService;
    }

    public Utilisateur getThom() {
        return thom;
    }

    public Utilisateur getEd() {
        return ed;
    }

    public Utilisateur getKaren() {
        return karen;
    }

    public Utilisateur getJulian() {
        return julian;
    }

    public Activite getGuitare() {
        return guitare;
    }

    public Activite getMuscu() {
        return muscu;
    }

    public Activite getPoker() {
        return poker;
    }

    public Activite getPingpong() {
        return pingpong;
    }

    public Activite getJogging() {
        return jogging;
    }

    public Activite getPhilo() {
        return philo;
    }

    public Activite getProcrastination() {
        return procrastination;
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
