package ca.cegep.incidents.controller;

import ca.cegep.incidents.model.Local;
import ca.cegep.incidents.service.LocalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/** CONTRÔLEUR REST : gestion des locaux. */
@RestController
@RequestMapping("/api/locaux")
public class LocalController {

    private final LocalService service;

    public LocalController(LocalService service) {
        this.service = service;
    }

    @GetMapping
    public List<Local> lister() {
        return service.lister();
    }

    @GetMapping("/{id}")
    public Local trouver(@PathVariable Long id) {
        return service.trouver(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Local creer(@RequestBody Map<String, String> corps) {
        return service.creer(corps.get("nom"), corps.get("batiment"));
    }

    @PutMapping("/{id}")
    public Local modifier(@PathVariable Long id, @RequestBody Map<String, String> corps) {
        return service.modifier(id, corps.get("nom"), corps.get("batiment"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void supprimer(@PathVariable Long id) {
        service.supprimer(id);
    }
}
