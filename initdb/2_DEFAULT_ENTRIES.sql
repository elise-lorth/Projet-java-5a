INSERT INTO defaultdb.users (first_name, last_name, age, email) VALUES ('Paul', 'Harrohide', 20,'Polharrohid@gmail.com');
INSERT INTO defaultdb.users (first_name, last_name, age, email) VALUES ('Xari', 'Xavier', 28,'leroidupaf@pax.fr');
INSERT INTO defaultdb.users (first_name, last_name, age, email) VALUES ('Alain', 'Posteur', 35,'among@us.com');
INSERT INTO defaultdb.users (first_name, last_name, age, email) VALUES ('Elvire', 'Debord', null,'marine@naval.fr');
INSERT INTO defaultdb.users (first_name, last_name, age, email) VALUES ('Aurelien', 'Desormière', 22, 'aurel.deso@epf.fr');
INSERT INTO defaultdb.users (first_name, last_name, age, email) VALUES ('PA', 'Domingo', 26,'domi@pax.com');
INSERT INTO defaultdb.users (first_name, last_name, age, email) VALUES ('Homer', 'Cipourtoux', 28,'ohmerde@gmail.com');
INSERT INTO defaultdb.users (first_name, last_name, age, email) VALUES ('Thomas', 'Bertier', 22,'thomthom@epf.fr');
INSERT INTO defaultdb.users (first_name, last_name, age, email) VALUES ('Harry', 'Covert', null,'arricot@legume.fr');
INSERT INTO defaultdb.users (first_name, last_name, age, email) VALUES ('Hugo', 'Delire', 25,'lautreroidupaf@hudog.fr');
INSERT INTO defaultdb.users (first_name, last_name, age, email) VALUES ('Elise','Lorthiois',21,'elise.lorthiois@epfedu.fr');


INSERT INTO defaultdb.rooms (name, capacity, screen, tablet, board, icon) VALUE ('Promenons-nous dans les bois',7,'3','2','1','images/forest.png');
INSERT INTO defaultdb.rooms (name, capacity, screen, tablet, board, icon) VALUE ('Wonderwall',8,'4','2','1','images/great-wall-of-china.png');
INSERT INTO defaultdb.rooms (name, capacity, screen, tablet, board, icon) VALUE ('L\'étrange maison de M. Jack',4,'2','2','1','images/haunted-house.png');
INSERT INTO defaultdb.rooms (name, capacity, screen, tablet, board, icon) VALUE ('This is sparta',4,'2','1','1','images/coliseum.png');
INSERT INTO defaultdb.rooms (name, capacity, screen, tablet, board, icon) VALUE ('La vie en rose',6,'3','2','1','images/eiffel-tower.png');
INSERT INTO defaultdb.rooms (name, capacity, screen, tablet, board, icon) VALUE ('Sexy beach',8,'4','2','1','images/beach.png');
INSERT INTO defaultdb.rooms (name, capacity, screen, tablet, board, icon) VALUE ('Camping paradis',3,'2','2','1','images/camping.png');
INSERT INTO defaultdb.rooms (name, capacity, screen, tablet, board, icon) VALUE ('Teubasco',2,'1','2','1','images/caravan.png');
INSERT INTO defaultdb.rooms (name, capacity, screen, tablet, board, icon) VALUE ('La route de la soie',4,'2','2','1','images/desert.png');
INSERT INTO defaultdb.rooms (name, capacity, screen, tablet, board, icon) VALUE ('L\'amour est dans le pré',5,'2','2','1','images/farm.png');
INSERT INTO defaultdb.rooms (name, capacity, screen, tablet, board, icon) VALUE ('Un jour j\'irais à New-York avec toi' ,9,'5','2','1','images/new-york.png');
INSERT INTO defaultdb.rooms (name, capacity, screen, tablet, board, icon) VALUE ('Le château arc-en-fiel',10,'5','2','1','images/palace.png');
INSERT INTO defaultdb.rooms (name, capacity, screen, tablet, board, icon) VALUE ('Sous l\'océaaan',7,'3','2','1','images/sea-bottom.png');
INSERT INTO defaultdb.rooms (name, capacity, screen, tablet, board, icon) VALUE ('Twilight',8,'4','2','1','images/sky.png');
INSERT INTO defaultdb.rooms (name, capacity, screen, tablet, board, icon) VALUE ('Blanc comme neige',5,'2','2','1','images/snowman.png');
INSERT INTO defaultdb.rooms (name, capacity, screen, tablet, board, icon) VALUE ('Volcanoroom',6,'3','2','1','images/volcano.png');
INSERT INTO defaultdb.rooms (name, capacity, screen, tablet, board, icon) VALUE ('Le grand bleu',2,'1','2','1','images/whale.png');
INSERT INTO defaultdb.reservations(room, start_date, end_date) VALUE ( 1, '2020-09-12T15:00:00', '2020-09-12T16:00:00');
INSERT INTO defaultdb.reservations(room, start_date, end_date) VALUE ( 1, '2020-09-12T17:00:00', '2020-09-12T19:00:00');
INSERT INTO defaultdb.reservations(room, start_date, end_date) VALUE ( 1, '2021-10-23T17:00:00', '2021-10-23T19:00:00');
INSERT INTO defaultdb.reservations(room, start_date, end_date) VALUE ( 1, '2021-10-23T20:00:00', '2021-10-23T21:00:00');
INSERT INTO defaultdb.reservations(room, start_date, end_date) VALUE ( 1, '2021-10-23T21:00:00', '2021-10-23T23:00:00');

#         screen = lits (1 pour 2)