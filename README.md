# Spring_tp4_projet

## Pré-requis
### Création de la BDD sous Ubuntu

 ```
 CREATE USER mstretti;
 ALTER ROLE mstretti WITH CREATEDB;
 CREATE DATABASE mstretti OWNER mstretti;
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