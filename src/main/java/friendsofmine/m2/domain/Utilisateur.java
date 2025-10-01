package friendsofmine.m2.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({"activites"})
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private @NotBlank String nom;
    private @NotBlank String prenom;

    @Email
    private @NotBlank String email;

    @Pattern(regexp = "[FM]")
    private @NotBlank String sexe;

    @OneToMany(mappedBy = "responsable") List<Activite> activites = new ArrayList<>();

    public Utilisateur(String nom, String prenom, String mail, String sexe) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = mail;
        this.sexe = sexe;
    }

    public Utilisateur() {
    }

    public void addActivite(Activite activite) {
        if (activite == null) {
            return;
        }

        if (!this.activites.contains(activite)) {
            this.activites.add(activite);
        }

        if (activite.getResponsable() != this) {
            activite.setResponsable(this);
        }
    }

    public void removeActivite(Activite activite) {
        if (activite == null) {
            return;
        }

        if (this.activites.remove(activite) && activite.getResponsable() == this) {
            activite.setResponsable(null);
        }
    }
}
