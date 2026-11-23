package ca.cegep.incidents.service;

import ca.cegep.incidents.repository.IncidentRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Squelettes de tests à compléter (voir ENONCE_PROJET.md).
 * Implémentez les 3 tests ci-dessous : 1 simple (sans mock), 1 sur une exception
 * (assertThrows), 1 avec mock (Mockito, vérifiez les interactions avec verify(...)).
 */
@ExtendWith(MockitoExtension.class)
class IncidentServiceTest {

    @Mock
    private IncidentRepository repository;

    @Test
    @Disabled("À implémenter : calculerDelaiCibleHeures HAUTE avec plafond (4h)")
    void calculerDelaiCibleHeures_prioriteHaute_estPlafonneA4Heures() {
        // Arrange : service sans dépendance à mocker pour ce test
        // Act : appeler calculerDelaiCibleHeures("HAUTE", ...) avec une valeur qui dépasse le plafond
        // Assert : le résultat ne doit jamais dépasser 4
    }

    @Test
    @Disabled("À implémenter : changerStatut sur un incident déjà FERME doit lever une exception (bogue à corriger avant ce test)")
    void changerStatut_incidentDejaFerme_lanceIllegalStateException() {
        // Arrange : mock repository.findById(...) retournant un incident avec statut FERME
        // Act + Assert : assertThrows(IllegalStateException.class, () -> ...)
        // Vérifiez aussi que repository.save(...) n'est jamais appelé (verify(repository, never())...)
    }

    @Test
    @Disabled("À implémenter : creer() doit enregistrer l'incident via le repository (mock)")
    void creer_incidentValide_estSauvegardeParLeRepository() {
        // Arrange : when(repository.save(any())).thenAnswer(...) pour retourner l'argument
        // Act : service.creer(...)
        // Assert : verify(repository).save(any())
    }
}
