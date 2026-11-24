# incidents-labo-api — Backend

![Build](https://github.com/KevinEJean/TP4-Maintenance-Backend/actions/workflows/pipeline-ci.yml/badge.svg)

API REST de signalement et de suivi des incidents sur les postes informatiques des locaux du Cégep.

## Démarrer le projet

```bash
./mvnw spring-boot:run
```

L'API démarre sur `http://localhost:8080`. La documentation interactive Swagger est disponible à
`http://localhost:8080/swagger-ui.html`.

## Lancer les tests

```bash
./mvnw test
```

## Architecture (packages)

| Package | Rôle |
|---|---|
| `model` | Entités du domaine (`Local`, `Poste`, `Incident`) |
| `repository` | Accès aux données, en mémoire (`Map`), avec données d'exemple préchargées |
| `service` | Logique métier et règles de validation |
| `controller` | Points d'entrée REST (`@RestController`) |
| `exception` | Exceptions métier et gestion centralisée des erreurs (`@RestControllerAdvice`) |

## Points d'entrée (endpoints) principaux

| Méthode | URL | Description |
|---|---|---|
| GET | `/api/locaux` | Liste des locaux |
| GET | `/api/locaux/{id}` | Détail d'un local |
| POST | `/api/locaux` | Créer un local |
| PUT | `/api/locaux/{id}` | Modifier un local |
| DELETE | `/api/locaux/{id}` | Supprimer un local |
| GET | `/api/postes?localId=` | Liste des postes (filtre optionnel par local) |
| GET | `/api/postes/{id}` | Détail d'un poste |
| POST | `/api/postes` | Créer un poste |
| PATCH | `/api/postes/{id}/etat` | Changer l'état d'un poste |
| DELETE | `/api/postes/{id}` | Supprimer un poste |
| GET | `/api/incidents` | Liste des incidents |
| GET | `/api/incidents/{id}` | Détail d'un incident |
| POST | `/api/incidents` | Signaler un incident |
| PUT | `/api/incidents/{id}` | Modifier un incident |
| PATCH | `/api/incidents/{id}/statut` | Changer le statut d'un incident |
| PATCH | `/api/incidents/{id}/assigner` | Assigner un technicien |
| DELETE | `/api/incidents/{id}` | Supprimer un incident |

## État du build

<!-- Remplacez OWNER/REPO par l'organisation et le dépôt réels de votre équipe -->
![Backend CI](https://github.com/OWNER/REPO/actions/workflows/backend-ci.yml/badge.svg)

## Modèle de données

Voir `ENONCE_PROJET.md` à la racine du projet pour le détail du modèle `Local` / `Poste` / `Incident`
et le backlog de fonctionnalités à implémenter.
