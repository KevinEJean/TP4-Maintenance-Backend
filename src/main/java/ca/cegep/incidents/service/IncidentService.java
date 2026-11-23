package ca.cegep.incidents.service;

import ca.cegep.incidents.exception.RessourceIntrouvableException;
import ca.cegep.incidents.model.Incident;
import ca.cegep.incidents.repository.IncidentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/** Logique métier des incidents. */
@Service
public class IncidentService {

    private final IncidentRepository repository;

    public IncidentService(IncidentRepository repository) {
        this.repository = repository;
    }

    public List<Incident> lister() {
        return repository.findAll();
    }

    public Incident trouver(Long id) {
        return repository.findById(id).orElseThrow(() -> new RessourceIntrouvableException("Incident", id));
    }

    // NOTE (bogue connu, à corriger) : posteId n'est jamais validé ici contre PosteRepository.
    // Un incident peut donc être créé avec un posteId qui ne correspond à aucun poste existant.
    public Incident creer(String description, String priorite, Long posteId, String technicienAssigne) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("La description est obligatoire");
        }
        if (priorite == null || priorite.isBlank()) {
            throw new IllegalArgumentException("La priorité est obligatoire");
        }
        if (posteId == null) {
            throw new IllegalArgumentException("Le posteId est obligatoire");
        }
        Incident incident = new Incident(null, description, priorite, "OUVERT", posteId,
                LocalDateTime.now(), technicienAssigne);
        return repository.save(incident);
    }

    public Incident modifier(Long id, String description, String priorite, Long posteId, String technicienAssigne) {
        Incident incident = trouver(id);
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("La description est obligatoire");
        }
        if (priorite == null || priorite.isBlank()) {
            throw new IllegalArgumentException("La priorité est obligatoire");
        }
        if (posteId == null) {
            throw new IllegalArgumentException("Le posteId est obligatoire");
        }
        incident.setDescription(description);
        incident.setPriorite(priorite);
        incident.setPosteId(posteId);
        incident.setTechnicienAssigne(technicienAssigne);
        return repository.save(incident);
    }

    public void supprimer(Long id) {
        trouver(id);
        repository.deleteById(id);
    }

    // NOTE (bogue connu, à corriger) : aucune vérification n'empêche de changer le statut d'un
    // incident déjà FERME. Un incident fermé devrait rester immuable.
    public Incident changerStatut(Long id, String nouveauStatut) {
        Incident incident = trouver(id);
        incident.setStatut(nouveauStatut);
        return repository.save(incident);
    }

    /**
     * Assigne un technicien à un incident et met à jour son statut.
     * Code smell volontaire (semaine 6) : Long Method — cette méthode mélange validation,
     * mise en forme du nom, journalisation et transition de statut, au lieu de déléguer
     * chacune de ces responsabilités à une méthode dédiée.
     */
    public Incident assignerTechnicien(Long id, String technicien) {
        Incident incident = trouver(id);

        if (technicien == null || technicien.isBlank()) {
            throw new IllegalArgumentException("Le nom du technicien est obligatoire");
        }

        String nomNettoye = technicien.trim();
        StringBuilder nomMisEnForme = new StringBuilder();
        for (int i = 0; i < nomNettoye.length(); i++) {
            char caractere = nomNettoye.charAt(i);
            if (i == 0) {
                nomMisEnForme.append(Character.toUpperCase(caractere));
            } else {
                nomMisEnForme.append(Character.toLowerCase(caractere));
            }
        }

        incident.setTechnicienAssigne(nomMisEnForme.toString());

        if (incident.getStatut().equals("OUVERT")) {
            incident.setStatut("ASSIGNE");
        } else if (incident.getStatut().equals("ASSIGNE")) {
            incident.setStatut("ASSIGNE");
        } else if (incident.getStatut().equals("EN_COURS")) {
            incident.setStatut("EN_COURS");
        } else {
            incident.setStatut("ASSIGNE");
        }

        System.out.println("Technicien " + incident.getTechnicienAssigne()
                + " assigné à l'incident #" + incident.getId());

        return repository.save(incident);
    }

    /**
     * Calcule le délai cible de résolution (en heures) selon la priorité, plafonné.
     * Deux smells volontaires (semaine 6) : nombres magiques (plafonds/facteurs codés en dur)
     * et chaîne de conditions répétée sur une String (la même structure « facteur × heures,
     * plafonné » est répétée 3 fois). À réusiner avec des constantes nommées et un enum Priorite
     * (voir aussi le smell Primitive Obsession documenté sur {@link ca.cegep.incidents.model.Incident}).
     */
    public int calculerDelaiCibleHeures(String priorite, int heuresEcoulees) {
        int delai = 0;
        if (heuresEcoulees > 0) {
            if (priorite.equals("HAUTE")) {
                delai = heuresEcoulees * 2;
                if (delai > 4) {
                    delai = 4;
                }
            } else if (priorite.equals("MOYENNE")) {
                delai = heuresEcoulees * 1;
                if (delai > 24) {
                    delai = 24;
                }
            } else {
                delai = heuresEcoulees / 2;
                if (delai > 72) {
                    delai = 72;
                }
            }
        }
        return delai;
    }
}
