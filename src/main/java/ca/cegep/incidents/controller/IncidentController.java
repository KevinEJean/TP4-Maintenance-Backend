package ca.cegep.incidents.controller;

import ca.cegep.incidents.model.Incident;
import ca.cegep.incidents.service.IncidentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/** CONTRÔLEUR REST : signalement et suivi des incidents. */
@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentService service;

    public IncidentController(IncidentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Incident> lister() {
        return service.lister();
    }

    @GetMapping("/{id}")
    public Incident trouver(@PathVariable Long id) {
        return service.trouver(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Incident creer(@RequestBody Map<String, Object> corps) {
        String description = (String) corps.get("description");
        String priorite = (String) corps.get("priorite");
        Long posteId = Long.valueOf(corps.get("posteId").toString());
        String technicienAssigne = (String) corps.get("technicienAssigne");
        return service.creer(description, priorite, posteId, technicienAssigne);
    }

    @PutMapping("/{id}")
    public Incident modifier(@PathVariable Long id, @RequestBody Map<String, Object> corps) {
        String description = (String) corps.get("description");
        String priorite = (String) corps.get("priorite");
        Long posteId = Long.valueOf(corps.get("posteId").toString());
        String technicienAssigne = (String) corps.get("technicienAssigne");
        return service.modifier(id, description, priorite, posteId, technicienAssigne);
    }

    @PatchMapping("/{id}/statut")
    public Incident changerStatut(@PathVariable Long id, @RequestBody Map<String, String> corps) {
        return service.changerStatut(id, corps.get("statut"));
    }

    @PatchMapping("/{id}/assigner")
    public Incident assignerTechnicien(@PathVariable Long id, @RequestBody Map<String, String> corps) {
        return service.assignerTechnicien(id, corps.get("technicien"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {
        service.supprimer(id);
    }

    @GetMapping("/{id}/delai-cible")
    public Map<String, Integer> delaiCible(@PathVariable Long id, @RequestParam int heuresEcoulees) {
        Incident incident = service.trouver(id);
        return Map.of("delaiCibleHeures", service.calculerDelaiCibleHeures(incident.getPriorite(), heuresEcoulees));
    }
}
