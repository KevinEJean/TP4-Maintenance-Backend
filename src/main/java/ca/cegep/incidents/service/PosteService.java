package ca.cegep.incidents.service;

import ca.cegep.incidents.exception.RessourceIntrouvableException;
import ca.cegep.incidents.model.Poste;
import ca.cegep.incidents.repository.LocalRepository;
import ca.cegep.incidents.repository.PosteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/** Logique métier des postes informatiques. */
@Service
public class PosteService {

    private final PosteRepository repository;
    private final LocalRepository localRepository;

    public PosteService(PosteRepository repository, LocalRepository localRepository) {
        this.repository = repository;
        this.localRepository = localRepository;
    }

    public List<Poste> lister() {
        return repository.findAll();
    }

    public List<Poste> listerParLocal(Long localId) {
        localRepository.findById(localId).orElseThrow(() -> new RessourceIntrouvableException("Local", localId));
        return repository.findByLocalId(localId);
    }

    public Poste trouver(Long id) {
        return repository.findById(id).orElseThrow(() -> new RessourceIntrouvableException("Poste", id));
    }

    public Poste creer(String numero, String etat, Long localId) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException("Le numéro du poste est obligatoire");
        }
        localRepository.findById(localId).orElseThrow(() -> new RessourceIntrouvableException("Local", localId));
        Poste poste = new Poste(null, numero, etat, localId);
        return repository.save(poste);
    }

    public Poste changerEtat(Long id, String nouvelEtat) {
        Poste poste = trouver(id);
        poste.setEtat(nouvelEtat);
        return repository.save(poste);
    }

    public void supprimer(Long id) {
        trouver(id);
        repository.deleteById(id);
    }
}
