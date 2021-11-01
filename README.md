# What is love hotel

Bienvenue sur notre interface de gestion et réservation de chambres.

Le what is love hotel est heureux de vous proposer des chambres variées et en libre accès 24/24,  7/7.

Comment lancer l'interface ?
===========

* Entrez dans l’invite de commande la ligne suivante :
`git clone https://github.com/elise-lorth/Projet-java-5a "What_is_love_hotel"` 

* Ouvrez docker desktop puis entrez ces deux lignes dans l'invite de commande :
`cd What_is_love_hotel`
`docker run --name mariadb --rm -e MYSQL_ROOT_PASSWORD=toor -e MYSQL_DATABASE=defaultdb -p 3306:3306 -v "%cd%/initdb:/docker-entrypoint-initdb.d" mariadb`

* Ouvrez un IDE pour lancer l’application.

* Si la base de donnée n’est pas configurée, créez dans l’IDE une database avec comme source mariaDB, host : localhost ; Port : 3306 ; User : root ; Password : toor ; Database : defaultdb, vérifiez que l’url est jdbc:mariadb://localhost:3306/defaultdb.
* Si elle ne se lance pas lancez manuellement les fichiers 1_TABLES et 2_DEFAULT_ENTRIES.
* Rendez-vous sur http://localhost:8080/

#Comment utiliser l’interface ?
* Avant de lancer le site nous vous conseillons de baisser un peu le volume de votre ordinateur.
* Sur l’**accueil publique** vous aurez la possibilité de voir les calendriers de chaque chambre. Sélectionnez une chambre dans la liste et naviguez dans son calendrier. En cliquant sur « mois », « semaine », « jour » et « planning » vous pourrez changer le mode d'affichage. Vous pourrez visualiser les informations de chaque réservation en cliquant dessus. 
* Sur l’**accueil administrateur**, vous pourrez voir toutes les salles disponibles à l’instant, toutes les salles de notre hôtel, et tous les clients. Il vous est possible d’ajouter de nouvelles salles et de nouveau membre, ainsi que de supprimer ou modifier ceux existants.
Vous pourrez aussi effectuer une réservation en remplissant un formulaire.
* Le formulaire de **réservation** permet de créer des reservations en suivant 2 étapes :
Dans un premier temps vous devez choisir les paramètres de la salle que vous voulez afin d'avoir une liste des salles remplissant les bonnes conditions.
Vous devez aussi choisir la date (fixe ou flexible) à laquelle vous voulez réserver la salle ainsi que la durée de la reservation, ce qui permet de trouver les salles libres à cette date.
Enfin après avoir recherché et choisi votre salle, vous pouvez inviter des membres à votre reservations et enfin la créer en cliquant sur Valider.

Nous espérons pouvoir vous compter bientôt parmi nos utilisateurs réguliers.