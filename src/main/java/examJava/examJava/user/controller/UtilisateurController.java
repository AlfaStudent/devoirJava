package examJava.examJava.user.controller;

import examJava.examJava.user.entity.Utilisateur;
import examJava.examJava.user.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    // Endpoint pour obtenir la liste de tous les utilisateurs
    @GetMapping
    public ResponseEntity<List<Utilisateur>> listerUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurService.listerUtilisateurs();
        return new ResponseEntity<>(utilisateurs, HttpStatus.OK);
    }

    // Endpoint pour obtenir un utilisateur par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> trouverUtilisateurParId(@PathVariable Long id) {
        Optional<Utilisateur> utilisateurOptional = utilisateurService.trouverUtilisateurParId(id);
        return utilisateurOptional.map(utilisateur -> new ResponseEntity<>(utilisateur, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint pour inscrire un nouvel utilisateur
    @PostMapping("/inscrire")
    public ResponseEntity<Utilisateur> inscrireUtilisateur(@RequestBody Utilisateur utilisateur) {
        Utilisateur nouvelUtilisateur = utilisateurService.inscrireUtilisateur(utilisateur);
        return new ResponseEntity<>(nouvelUtilisateur, HttpStatus.CREATED);
    }

    // Endpoint pour mettre à jour les informations d'un utilisateur
    @PutMapping("/mettre-a-jour")
    public ResponseEntity<Utilisateur> mettreAJourUtilisateur(@RequestBody Utilisateur utilisateur) {
        Utilisateur utilisateurMisAJour = utilisateurService.mettreAJourUtilisateur(utilisateur);
        return new ResponseEntity<>(utilisateurMisAJour, HttpStatus.OK);
    }

    // Endpoint pour supprimer un utilisateur par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerUtilisateur(@PathVariable Long id) {
        utilisateurService.supprimerUtilisateur(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
