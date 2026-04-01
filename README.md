# JavaEatDishesUsers

Microservice REST en **Java 21 / Jakarta EE** pour la gestion des **plats** (Dishes) et des **utilisateurs** (Users) dans le cadre du module **Architecture Logicielle R4.01**.

## Description

Ce service expose une API REST complète (CRUD) pour :

- créer, lire, mettre à jour et supprimer des plats ;
- créer, lire, mettre à jour et supprimer des utilisateurs.

Le projet suit une architecture en couches stricte afin de séparer clairement les responsabilités, améliorer la maintenabilité et faciliter les évolutions.

## Documentation API

- 📁 **JavaDoc versionnée dans le dépôt** :
	- `docs/apidocs/index.html`
## Architecture en Couches (Layered Architecture)

### 1. Model

La couche **Model** contient les entités métier (ex. `Dish`, `User`) utilisées pour transporter et structurer les données dans toute l'application.

### 2. Repository

La couche **Repository** est responsable de l'accès aux données MariaDB via JDBC :

- exécution des requêtes SQL ;
- mapping SQL ↔ objets Java ;
- encapsulation de la logique de persistance.

### 3. Service

La couche **Service** porte la logique métier :

- validations fonctionnelles (ex. prix strictement positif, format email) ;
- orchestration entre Resource et Repository ;
- centralisation des règles métier.

### 4. Resource

La couche **Resource** expose les endpoints REST (JAX-RS) :

- réception des requêtes HTTP ;
- sérialisation JSON ;
- retour des codes de statut HTTP appropriés.

## Choix Techniques et Sécurité

- 🔒 **Pattern Singleton** : la connexion base de données est centralisée via une instance réutilisable dans `DatabaseConnection`.
- **Injection de dépendances (CDI)** : les dépendances inter-couches sont injectées avec `@Inject`.
- **Prévention des injections SQL** : utilisation de **requêtes préparées JDBC** (`PreparedStatement`) pour toutes les opérations SQL.
- **Gestion des secrets** : chargement des variables via `dotenv-java` avec **fallback** sur les VM Options GlassFish (`DB_URL`, `DB_USER`, `DB_PASSWORD`).

## Endpoints de l'API (CRUD)

Base path de l'application : `/api`

### Dishes

| Méthode | Route | Description |
|---|---|---|
| GET | `/api/dishes` | Récupère la liste des plats |
| GET | `/api/dishes/{id}` | Récupère un plat par identifiant |
| POST | `/api/dishes` | Crée un nouveau plat |
| PUT | `/api/dishes/{id}` | Met à jour un plat existant |
| DELETE | `/api/dishes/{id}` | Supprime un plat |

### Users

| Méthode | Route | Description |
|---|---|---|
| GET | `/api/users` | Récupère la liste des utilisateurs |
| GET | `/api/users/{id}` | Récupère un utilisateur par identifiant |
| POST | `/api/users` | Crée un nouvel utilisateur |
| PUT | `/api/users/{id}` | Met à jour un utilisateur existant |
| DELETE | `/api/users/{id}` | Supprime un utilisateur |

## Configuration et Lancement

### Prérequis

- Java 21
- Maven (ou wrapper Maven `mvnw`)
- Serveur **MariaDB** accessible
- Serveur d'application **GlassFish 7** configuré

### Variables de configuration requises

Configurer les variables suivantes (fichier `.env` ou VM Options GlassFish) :

```bash
DB_URL=jdbc:mariadb://localhost:3306/javaeat
DB_USER=your_user
DB_PASSWORD=your_password
```

### Compilation

```bash
./mvnw -DskipTests compile
```

### Génération JavaDoc

```bash
./mvnw -DskipTests javadoc:javadoc
```

## Contexte Pédagogique

Projet réalisé dans le cadre du module **R4.01 - Architecture Logicielle**.
L'objectif principal est de démontrer une implémentation propre d'une API REST Jakarta EE basée sur une architecture en couches.
