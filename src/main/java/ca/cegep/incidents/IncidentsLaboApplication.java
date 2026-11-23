package ca.cegep.incidents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Point d'entrée de l'API de signalement des postes informatiques problématiques des labos. */
@SpringBootApplication
public class IncidentsLaboApplication {

    public static void main(String[] args) {
        SpringApplication.run(IncidentsLaboApplication.class, args);
    }
}
