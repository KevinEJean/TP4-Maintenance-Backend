package ca.cegep.incidents.repository;

import ca.cegep.incidents.model.Poste;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/** Accès aux données : les postes sont gardés en mémoire (perdus à l'arrêt de l'application). */
@Repository
public class PosteRepository {

    private final Map<Long, Poste> postes = new LinkedHashMap<>();
    private long prochainId = 1;

    public PosteRepository() {
        // Local 1 = S-021
        save(new Poste(null, "Poste 1", "FONCTIONNEL", 1L));
        save(new Poste(null, "Poste 2", "FONCTIONNEL", 1L));
        save(new Poste(null, "Poste 3", "HORS_SERVICE", 1L));
        // Local 2 = S-028
        save(new Poste(null, "Poste 1", "FONCTIONNEL", 2L));
        save(new Poste(null, "Poste 2", "FONCTIONNEL", 2L));
        // Local 4 = R-105Lab
        save(new Poste(null, "Poste 1", "FONCTIONNEL", 4L));
    }

    public List<Poste> findAll() {
        return new ArrayList<>(postes.values());
    }

    public List<Poste> findByLocalId(Long localId) {
        return postes.values().stream()
                .filter(poste -> poste.getLocalId().equals(localId))
                .collect(Collectors.toList());
    }

    public Optional<Poste> findById(Long id) {
        return Optional.ofNullable(postes.get(id));
    }

    public Poste save(Poste poste) {
        if (poste.getId() == null) {
            poste.setId(prochainId++);
        }
        postes.put(poste.getId(), poste);
        return poste;
    }

    public void deleteById(Long id) {
        postes.remove(id);
    }
}
