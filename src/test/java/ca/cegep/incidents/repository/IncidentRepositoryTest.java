package ca.cegep.incidents.repository;

import ca.cegep.incidents.model.Incident;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class IncidentRepositoryTest {

    private final IncidentRepository repository = new IncidentRepository();

    @Test
    void findAll_retourneLesIncidentsInitiaux() {
        List<Incident> incidents = repository.findAll();

        assertEquals(2, incidents.size());
    }

    @Test
    void save_assigneUnIdQuandAbsent() {
        Incident incident = new Incident(null, "Test", "BASSE", "OUVERT", 1L, LocalDateTime.now(), null);

        Incident sauvegarde = repository.save(incident);

        assertNotNull(sauvegarde.getId());
    }

    @Test
    void findById_retourneVideQuandIdInconnu() {
        Optional<Incident> resultat = repository.findById(999L);

        assertTrue(resultat.isEmpty());
    }

    @Test
    void deleteById_retireLIncidentDeLaListe() {
        Incident incident = repository.save(
                new Incident(null, "À supprimer", "BASSE", "OUVERT", 1L, LocalDateTime.now(), null));

        repository.deleteById(incident.getId());

        assertTrue(repository.findById(incident.getId()).isEmpty());
    }
}
