package examJava.examJava.livre.entity;

import examJava.examJava.user.entity.Utilisateur;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
public class Livre {

    @Id
    private Long id;
    private String titre;
    private String auteur;
    private String datePublication;
    private int exemplairesDisponibles;

    public Livre() {

    }

    public Livre(String titre, String auteur, String datePublication, int exemplairesDisponibles) {
        this.titre = titre;
        this.auteur = auteur;
        this.datePublication = datePublication;
        this.exemplairesDisponibles = exemplairesDisponibles;
    }

    // ce sont là les Getters et Setters
    // j'ai decidé de les faire differemment contrairement a Utilisateur oû j'ai uitilisé les annotations

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public int getExemplairesDisponibles() {
        return exemplairesDisponibles;
    }

    public void setExemplairesDisponibles(int exemplairesDisponibles) {
        this.exemplairesDisponibles = exemplairesDisponibles;
    }

    @ManyToOne
    @JoinColumn(name = "emprunteur_id")
    private Utilisateur emprunteur;

    public void setEmprunteur(Utilisateur emprunteur) {
        this.emprunteur = emprunteur;
        emprunteur.getLivresEmpruntes().add(this);
    }


}
