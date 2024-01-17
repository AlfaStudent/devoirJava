package examJava.examJava.livre.controller;


import examJava.examJava.livre.entity.Livre;
import examJava.examJava.livre.services.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livres")
public class LivreController {
    private final LivreService livreService;

    @Autowired
    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    // Endpoint pour obtenir la liste de tous les livres
    @GetMapping
    public ResponseEntity<List<Livre>> listerLivres() {
        List<Livre> livres = livreService.listerLivres();
        return new ResponseEntity<>(livres, HttpStatus.OK);
    }

    // Endpoint pour obtenir un livre par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Livre> trouverLivreParId(@PathVariable Long id) {
        Optional<Livre> livreOptional = livreService.trouverLivreParId(id);
        return livreOptional.map(livre -> new ResponseEntity<>(livre, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint pour ajouter un nouveau livre
    @PostMapping("/ajouter")
    public ResponseEntity<Livre> ajouterLivre(@RequestBody Livre livre) {
        Livre nouveauLivre = livreService.ajouterLivre(livre);
        return new ResponseEntity<>(nouveauLivre, HttpStatus.CREATED);
    }

    // Endpoint pour supprimer un livre par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerLivre(@PathVariable Long id) {
        livreService.supprimerLivre(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
