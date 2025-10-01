package friendsofmine.m2.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Activite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private @NotBlank String titre;
    private String descriptif;
    @ManyToOne
    @NotNull
    Utilisateur responsable;

    public Activite(String titre, String descriptif) {
        this.titre = titre;
        this.descriptif = descriptif;
    }

    public Activite() {
    }

    public Activite(String titre, String descriptif, Utilisateur responsable) {
        this.titre = titre;
        this.descriptif = descriptif;
        setResponsable(responsable);
    }

    public void setResponsable(Utilisateur responsable) {
        if (this.responsable == responsable) {
            return;
        }

        if (this.responsable != null) {
            this.responsable.getActivites().remove(this);
        }

        this.responsable = responsable;

        if (responsable != null && !responsable.getActivites().contains(this)) {
            responsable.getActivites().add(this);
        }
    }
}
