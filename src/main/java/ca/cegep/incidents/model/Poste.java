package ca.cegep.incidents.model;

/** MODÈLE : un poste informatique (ordinateur) situé dans un {@link Local}. */
public class Poste {

    private Long id;
    private String numero;
    private String etat; // FONCTIONNEL ou HORS_SERVICE
    private Long localId;

    public Poste() {
    }

    public Poste(Long id, String numero, String etat, Long localId) {
        this.id = id;
        this.numero = numero;
        this.etat = etat;
        this.localId = localId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Long getLocalId() {
        return localId;
    }

    public void setLocalId(Long localId) {
        this.localId = localId;
    }
}
