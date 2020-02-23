# Application de gestion de participants et d'événements (Spring projet)

## Objectifs
Ecrire une petite application de gestion des inscriptions de participants à des évenements. Nous utiliserons le Framework Spring pour l’architecture MVC, Hibernate pour la couche de persitance, Maven pour la gestion des dépendances.

## Fonctionnalités principales à implémenter
* Affichage de la liste des participants dans un tableau HTML
  * dans un contrôleur dédié
  * persitance avec Hibernate (les données sont dans une base PostgreSQL ou MySQL)
* Intégration de la classe Evenement dans la persistance des données Hibernate ainsi que l’association entre les deux classes Participant et Evenement
* Créer un formulaire d’ajout de participants
  * avec inscription à un évenement au moins
  * dans un contrôleur dédié à cette tâche
* Pour les vues (qui peuvent rester très succinctes), vous pouvez utiliser un moteur de templates comme thymeleaf.

Toutes ces fonctionnalités ont été implémentées.

## Fonctionnalités annexes
* Modification de participants
* Suppression de participants

## Pré-requis
### Création de la BDD sous Ubuntu

* Installer MySQL sur la machine
L'installation ajoute un nouvel utilisateur linux et un utilisateur de la base de données nommé mysql. Il est seul autorisé, pour l'instant, à se connecter à la base de données. Il ne possède pas de mot de passe, et par mesure de sécurité nous ne lui en attribuerons pas. Nous allons créer un nouvel utilisateur.
* Connexion à la bdd avec l'utilisateur mysql :
```
sudo -i -u postgres
```

* Lancer l'invite de commande MySQL à l'aide de la commande `mysql`.
Vous êtes désormais connecté à MySQL en mode administrateur.

* Création d'un utilisateur portant le même nom que l'utilisateur linux (`mstretti`)
 ```
 CREATE USER mstretti;
 ```
* Ajout des droits de créer une base de données
 ```
 ALTER ROLE mstretti WITH CREATEDB;
 ```
 * Crétion de la BDD portant le même nom que l'utilisateur (`mstretti`)
 ```
 CREATE DATABASE mstretti OWNER mstretti;
 ```
 * Ajout d'un mot de passe à l'utilisateur
 ```
 ALTER USER mstretti WITH ENCRYPTED PASSWORD 'mstretti';
 ```
 
### Ajout d'événements
```
http://localhost:8080/evenement/add?intitule=Régate&theme=Sport
http://localhost:8080/evenement/add?intitule=Les Fourberies de Scapin&theme=Théâtre
```

Requête SQL pour remplir les autres colonnes de la table si besoin :
```
UPDATE `evenement` SET `date_debut` = '2020-04-15', `description` = 'Régate départementale', `duree` = '3', `nb_part_max` = '50', `organisateur` = 'ASPTT Marseille' WHERE `evenement`.`num_event` = 1;

```
**Attention :** Si l'événement *Régate* n'est pas le 1er événement, il faut changer le `num_event` !
```
UPDATE `evenement` SET `date_debut` = '2020-03-06', `description` = 'Représentation des Fourberies de Scapin', `duree` = '2', `nb_part_max` = '70', `organisateur` = 'La Comédie Française' WHERE `evenement`.`num_event` = 2;
```
**Attention :** Si l'événement *Les Fourberies de Scapin* n'est pas le 2ème événement, il faut changer le `num_event` !
