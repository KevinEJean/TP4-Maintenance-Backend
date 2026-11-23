package ca.cegep.incidents.exception;

/** Exception levée quand une ressource (Local, Poste ou Incident) demandée n'existe pas. */
public class RessourceIntrouvableException extends RuntimeException {

    public RessourceIntrouvableException(String typeRessource, Long id) {
        super(typeRessource + " introuvable avec l'id " + id);
    }
}
