# Projet-java-5a

Réservation de rooms de réunion
===========
L'objectif de ce projet est de créer une application de réservation de rooms. 
# Le projet 
Vous devez créer à la fois une section `publique` et une section `administration`.
N'importe quel utilisateur doit pouvoir réserver une room pour une durée spécifique à la date et l'heure qu'il veut, ou bien dès que la room est disponible. 
Chaque room de réunion a un dashboard où tout le monde peut voir les disponibilités et réserver la room (on peut imaginer placer une tablette devant chaque room avec ce dashboard).

En particulier, les fonctionnalités suivantes seront requises :

**Panneau d'administration:**
  * Créer et éditer un membre: nom, e-mail, date de naissance, id
  * Créer et éditer une room : nom, photo, capacité (X personnes), équipement (écran, tableau, ...), id
  * Réserver une room à une date et heure spécifique avec des contraintes
  * Voir quelles rooms sont libres
  * Inviter des membres à la room de réunion
  * Envoyer des e-mails aux membres invités (avec une carte ICS pouvant être lier à un calendrier)

**Partie publique:**
  * Voir le calendrier d'une room de réunion
  * Voir les membres d'une réunion
  * Réserver une room

Un **membre** peut être édité. Il a un nom, un email, une date de naissance. Chaque utilisateur a un identifiant unique (uuid). 

Une **room de réunion** a un nom, une photo, une capacité et un identifiant unique. Elle peut avoir plus d'attributs ;).

Vous devrez implémenter un ensemble de règles de réservation et de contraintes pour réserver une room comme : 
 - J'ai besoin d'un room pour X personnes
 - J'en ai besoin pour Y heures et Z minutes
 - J'ai besoin d'une télévision pour afficher mes diapos
 - J'ai besoin d'un tableau
 - De préférence le matin
 - Dès que possible / la semaine prochaine / le mois prochain
 - De préférence dans la room 1 / room 1 ou 3

Certaines contraintes ou combinaisons de contraintes ne seront pas possible, il faudra en informer l'utilisateur et proposer une meilleur solution ou une alternative...

Vous n'avez pas à implémenter la sécurité comme la connexion ou le compte utilisateur pour le moment.

# Bonus
Vous avez déjà **tout terminé** ?
Voici quelques features qui rendront votre plateforme encore plus attrayante :

 * Faire un lien avec Google Calendar
 * Ajouter un moteur de recommandations dans le cas où une room n'est pas disponible
 * Envoyer des rappels de réunion par e-mail 
 * Implémenter les tests de votre backend. 



# Training Spring Boot
=====

1. Importer le projet dans IntelliJ IDEA en important le fichier "pom.xml" à la racine de ce répertoire

2. Exécuter votre DB mysql. Si vous avez docker, vous pouvez utiliser la commande suivante:
```
docker run --name mariadb --rm -e MYSQL_ROOT_PASSWORD=toor -e MYSQL_DATABASE=defaultdb -p 3306:3306 -v "`pwd`/initdb:/docker-entrypoint-initdb.d" mariadb
```

3. Si vous n'avez pas Docker, et que vous avez un serveur MariaDB custom, vérifiez bien que vos utilisateurs / mdp sont les bons par rapport au fichier de configuration (src/main/resources/application.properties), et exécutez les scripts présents dans le dossier `initdb`

4. Tous les scripts sql contenus dans le dossier initdb seront exécutés automatiquement lors du premier chargement de la DB.

5. Lancez l'application via IntelliJ, et vérifiez qu'elle fonctionne sur http://localhost:8080 (par défaut)