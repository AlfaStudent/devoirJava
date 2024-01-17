package examJava.examJava.livre.services;


import examJava.examJava.livre.entity.Livre;
import examJava.examJava.livre.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//les differentes fonction qui seront consommeer par le controller livreController
@Service
public class LivreService {

    private final LivreRepository livreRepository;

    @Autowired
    public LivreService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }
    public List<Livre> listerLivres() {
        return livreRepository.findAll();
    }

    public Optional<Livre> trouverLivreParId(Long id) {
        return livreRepository.findById(id);
    }

    public Livre ajouterLivre(Livre livre) {
        return livreRepository.save(livre);
    }

    public void supprimerLivre(Long id) {
        livreRepository.deleteById(id);
    }






}
