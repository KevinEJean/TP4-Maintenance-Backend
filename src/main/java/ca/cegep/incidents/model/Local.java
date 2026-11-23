package ca.cegep.incidents.model;

/** MODÈLE (le « M » de MVC) : un local (classe/labo) du Département, ex. S-021. */
public class Local {

    private Long id;
    private String nom;
    private String batiment;

    public Local() {
    }

    public Local(Long id, String nom, String batiment) {
        this.id = id;
        this.nom = nom;
        this.batiment = batiment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getBatiment() {
        return batiment;
    }

    public void setBatiment(String batiment) {
        this.batiment = batiment;
    }
}
