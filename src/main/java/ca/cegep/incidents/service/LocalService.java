package ca.cegep.incidents.service;

import ca.cegep.incidents.exception.RessourceIntrouvableException;
import ca.cegep.incidents.model.Local;
import ca.cegep.incidents.repository.LocalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/** Logique métier des locaux. */
@Service
public class LocalService {

    private final LocalRepository repository;

    public LocalService(LocalRepository repository) {
        this.repository = repository;
    }

    public List<Local> lister() {
        return repository.findAll();
    }

    public Local trouver(Long id) {
        return repository.findById(id).orElseThrow(() -> new RessourceIntrouvableException("Local", id));
    }

    public Local creer(String nom, String batiment) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("Le nom du local est obligatoire");
        }
        Local local = new Local(null, nom, batiment);
        return repository.save(local);
    }

    public Local modifier(Long id, String nom, String batiment) {
        Local local = trouver(id);
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("Le nom du local est obligatoire");
        }
        local.setNom(nom);
        local.setBatiment(batiment);
        return repository.save(local);
    }

    public void supprimer(Long id) {
        trouver(id);
        repository.deleteById(id);
    }
}
