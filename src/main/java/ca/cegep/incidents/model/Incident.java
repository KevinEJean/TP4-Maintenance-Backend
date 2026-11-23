package ca.cegep.incidents.model;

import java.time.LocalDateTime;

/**
 * MODÈLE : un incident signalé par un étudiant sur un {@link Poste} d'un local.
 * Code smell volontaire (semaine 6) : Primitive Obsession — {@code priorite} et {@code statut}
 * sont représentés par de simples {@code String} partout dans le code (modèle, service,
 * contrôleur) plutôt que par des enums, ce qui autorise des valeurs invalides et disperse la
 * logique de validation/transition. À réusiner avec des enums (ex. {@code Priorite}, {@code Statut}).
 */
public class Incident {

    private Long id;
    private String description;
    private String priorite; // BASSE, MOYENNE ou HAUTE
    private String statut; // OUVERT, ASSIGNE, EN_COURS, RESOLU ou FERME
    private Long posteId;
    private LocalDateTime dateSignalement;
    private String technicienAssigne;

    public Incident() {
    }

    public Incident(Long id, String description, String priorite, String statut, Long posteId,
            LocalDateTime dateSignalement, String technicienAssigne) {
        this.id = id;
        this.description = description;
        this.priorite = priorite;
        this.statut = statut;
        this.posteId = posteId;
        this.dateSignalement = dateSignalement;
        this.technicienAssigne = technicienAssigne;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriorite() {
        return priorite;
    }

    public void setPriorite(String priorite) {
        this.priorite = priorite;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Long getPosteId() {
        return posteId;
    }

    public void setPosteId(Long posteId) {
        this.posteId = posteId;
    }

    public LocalDateTime getDateSignalement() {
        return dateSignalement;
    }

    public void setDateSignalement(LocalDateTime dateSignalement) {
        this.dateSignalement = dateSignalement;
    }

    public String getTechnicienAssigne() {
        return technicienAssigne;
    }

    public void setTechnicienAssigne(String technicienAssigne) {
        this.technicienAssigne = technicienAssigne;
    }
}
