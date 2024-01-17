package examJava.examJava.user.entity;
import examJava.examJava.livre.entity.Livre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String email;
    private String motDePasse;

    @OneToMany(mappedBy = "emprunteur", cascade = CascadeType.ALL)
    private Set<Livre> livresEmpruntes = new HashSet<>();



    // Méthode pour récupérer les livres empruntés par un utilisateur
    //difficulter a implementer entierement , je prevois de le refaire
    public Set<Livre> getLivresEmpruntes() {
        return livresEmpruntes;
    }


}
