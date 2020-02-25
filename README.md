# Application de gestion de participants et d'événements (Spring projet)

## Objectifs
Ecrire une petite application de gestion des inscriptions de participants à des évenements. Utilisation du Framework Spring pour l’architecture MVC, Hibernate pour la couche de persitance, Maven pour la gestion des dépendances.

## Fonctionnalités implémentées
* Affichage de la liste des participants dans un tableau HTML
* Création d'un formulaire d’ajout de participants pour un événement
* Modification de participants
* Suppression de participants  
  
+ Affichage de la liste des événements dans un tableau HTML
+ Création d'un formulaire d’ajout d'événements
+ Modification d'événements
+ Suppression d'événements (qui supprime également tous les participants inscrits à cet événement)

## Création de la BDD sous Ubuntu

* Installer MySQL sur la machine
```
sudo apt-get install mysql
sudo apt install mysql-client
```

* Connexion à mysql :
```
sudo mysql
```
* Création d'un utilisateur (`mstretti`) pour cette bdd :
 ```
 CREATE USER 'mstretti'@'%' IDENTIFIED BY 'mstretti';
 ```
* Ajout de tous les droits sur cette bdd pour cet utilisateur :
 ```
 GRANT ALL ON mstretti *.* TO 'mstretti'@'%';
 ```
 
## Lancement de l'application
Dans un IDE (Eclipse, NetBeans), il faut build le projet et le lancer ensuite.

L'application se lance à l'adresse `localhost:8080`.
