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
        return new Activite("Guitare", "Matériel non fourni", getThom());
    }

    public Activite getMuscu() {
        return new Activite("Muscu", "Créneau réservé le mardi", getEd());
    }

    public Activite getPoker() {
        return new Activite("Poker", "Petite blind à 1 euro", getKaren());
    }

    public Activite getPingpong() {
        return new Activite("Ping Pong", "Matériel non fourni", getJulian());
    }

    public Activite getJogging() {
        return new Activite("Jogging", "Tous les midis", getEd());
    }

    public Activite getPhilo() {
        return new Activite("Philo", "Le club des admirateurs de Socrate", getThom());
    }

    public Activite getProcrastination() {
        return new Activite("Procrastination", "On verra demain", getThom());
    }

    public void initUtilisateurs() {
        utilisateurService.saveUtilisateur(getThom());
        utilisateurService.saveUtilisateur(getEd());
        utilisateurService.saveUtilisateur(getKaren());
        utilisateurService.saveUtilisateur(getJulian());
    }

    public void initActivites() {
        activiteService.saveActivite(getGuitare());
        activiteService.saveActivite(getMuscu());
        activiteService.saveActivite(getPoker());
        activiteService.saveActivite(getPingpong());
        activiteService.saveActivite(getJogging());
        activiteService.saveActivite(getPhilo());
        activiteService.saveActivite(getProcrastination());
    }

    @Override
    public void run(ApplicationArguments args) {
        initUtilisateurs();
        initActivites();
    }
}
