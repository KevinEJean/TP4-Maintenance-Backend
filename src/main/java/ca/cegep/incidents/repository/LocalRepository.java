package ca.cegep.incidents.repository;

import ca.cegep.incidents.model.Local;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Accès aux données : les locaux sont gardés en mémoire (perdus à l'arrêt de l'application). */
@Repository
public class LocalRepository {

    private final Map<Long, Local> locaux = new LinkedHashMap<>();
    private long prochainId = 1;

    public LocalRepository() {
        save(new Local(null, "S-021", "S"));
        save(new Local(null, "S-028", "S"));
        save(new Local(null, "S-013", "S"));
        save(new Local(null, "R-105Lab", "R"));
        save(new Local(null, "R-108LAB", "R"));
    }

    public List<Local> findAll() {
        return new ArrayList<>(locaux.values());
    }

    public Optional<Local> findById(Long id) {
        return Optional.ofNullable(locaux.get(id));
    }

    public Local save(Local local) {
        if (local.getId() == null) {
            local.setId(prochainId++);
        }
        locaux.put(local.getId(), local);
        return local;
    }

    public void deleteById(Long id) {
        locaux.remove(id);
    }
}
