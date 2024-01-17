package examJava.examJava.user.services;

// UtilisateurService.java
import examJava.examJava.livre.entity.Livre;
import examJava.examJava.livre.repository.LivreRepository;
import examJava.examJava.user.entity.Utilisateur;
import examJava.examJava.user.repository.UtilisateurRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final LivreRepository livreRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository, LivreRepository livreRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.livreRepository = livreRepository;
    }

    public List<Utilisateur> listerUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> trouverUtilisateurParId(Long id) {
        return utilisateurRepository.findById(id);
    }

    public Utilisateur inscrireUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur mettreAJourUtilisateur(Utilisateur utilisateur) {
        Optional<Utilisateur> utilisateurExist = utilisateurRepository.findById(utilisateur.getId());
        if (utilisateurExist.isPresent()) {
            // L'utilisateur existe, je met a jour les informations
            Utilisateur existingUtilisateur = utilisateurExist.get();
            existingUtilisateur.setNom(utilisateur.getNom());
            existingUtilisateur.setEmail(utilisateur.getEmail());
            return utilisateurRepository.save(existingUtilisateur);
        } else {
            // L'utilisateur n'existe pas, je choisir de lever une exception
            throw new EntityNotFoundException("Utilisateur avec ID " + utilisateur.getId() + " non trouvé.");
        }
    }

    public void supprimerUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    //je n'ai pas reussi a implementer totalement la notion d'"mprunt,
    public void emprunterLivre(Long idUtilisateur, Long idLivre) {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(idUtilisateur);
        Optional<Livre> livreOptional = livreRepository.findById(idLivre);


        if (utilisateurOptional.isPresent() && livreOptional.isPresent()) {
            Utilisateur utilisateur = utilisateurOptional.get();
            Livre livre = livreOptional.get();

            if (livre.getExemplairesDisponibles() > 0) {
                livre.setExemplairesDisponibles(livre.getExemplairesDisponibles() - 1);
                livre.setEmprunteur(utilisateur);
                utilisateur.getLivresEmpruntes().add(livre);
                utilisateurRepository.save(utilisateur);
            }
        }
    }}
