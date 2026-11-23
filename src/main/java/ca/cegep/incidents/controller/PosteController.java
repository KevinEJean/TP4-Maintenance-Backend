package ca.cegep.incidents.controller;

import ca.cegep.incidents.model.Poste;
import ca.cegep.incidents.service.PosteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/** CONTRÔLEUR REST : gestion des postes informatiques. */
@RestController
@RequestMapping("/api/postes")
public class PosteController {

    private final PosteService service;

    public PosteController(PosteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Poste> lister(@RequestParam(required = false) Long localId) {
        if (localId != null) {
            return service.listerParLocal(localId);
        }
        return service.lister();
    }

    @GetMapping("/{id}")
    public Poste trouver(@PathVariable Long id) {
        return service.trouver(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Poste creer(@RequestBody Map<String, Object> corps) {
        String numero = (String) corps.get("numero");
        String etat = (String) corps.getOrDefault("etat", "FONCTIONNEL");
        Long localId = Long.valueOf(corps.get("localId").toString());
        return service.creer(numero, etat, localId);
    }

    @PatchMapping("/{id}/etat")
    public Poste changerEtat(@PathVariable Long id, @RequestBody Map<String, String> corps) {
        return service.changerEtat(id, corps.get("etat"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {
        service.supprimer(id);
    }
}
