package examJava.examJava.livre.repository;

import examJava.examJava.livre.entity.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivreRepository extends JpaRepository<Livre, Long> {
}
