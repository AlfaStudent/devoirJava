package examJava.examJava.user.repository;

// UtilisateurRepository.java
import examJava.examJava.user.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    // Ajoutez des méthodes personnalisées si nécessaires
}
