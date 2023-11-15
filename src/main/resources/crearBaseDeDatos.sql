CREATE DATABASE IF NOT EXISTS data;
USE data;

CREATE TABLE IF NOT EXISTS Palo 
(
	id INT PRIMARY KEY auto_increment,
    palo VARCHAR(20)
);


CREATE TABLE IF NOT EXISTS Sala (
	id_sala INT PRIMARY KEY  auto_increment,
    nombre_sala VARCHAR(100),
cantidadMAximaJugadores INT,
Cantidad_de_jugadores_en_sala INT
);


INSERT INTO Palo (palo) VALUES
	('oro'),('espada'),('copa'),('basto');
    
CREATE TABLE IF NOT EXISTS Carta 
(
	id INT PRIMARY KEY auto_increment,
    nro	INT,
	palo INT,
    valor INT,
    imagen	VARCHAR(512),
    FOREIGN KEY (palo) REFERENCES Palo(id)
);
-- select c.id, c.nro, p.palo, c.valor, c.imagen from Carta AS C
-- join Palo AS p ON p.id = c.palo;
DROP TABLE IF EXISTS Usuario;
CREATE TABLE Usuario (
  id int PRIMARY KEY auto_increment,
  nombre varchar(40) NOT NULL,
  apellido varchar(40) NOT NULL,
  email varchar(40) NOT NULL,
  username varchar(40) NOT NULL,
  password varchar(40) NOT NULL,
  rol varchar(10) NOT NULL,
  activo boolean NOT NULL,
  puntos_ranking int not null   );

INSERT INTO Usuario (nombre, apellido, email, username, password,rol,activo,puntos_ranking) VALUES ("Rocio","Crespo","rocio@gmail.com","rocio1234","1234","ADMIN",False,50);
INSERT INTO Usuario (nombre, apellido, email, username, password,rol,activo,puntos_ranking) VALUES ("Joaquin","Oviedo","joaco_oviedo2@hotmail.com","joa1234","1234","ADMIN",False,30);
# INSERT INTO Carta (nro, palo, valor, imagen) VALUES
# 	('1', '1', '8', 'oro1.png'),
# 	('2', '1', '9', 'oro2.png'),
# 	('3', '1', '10', 'oro3.png'),
# 	('4', '1', '1', 'oro4.png'),
# 	('5', '1', '2', 'oro5.png'),
# 	('6', '1', '3', 'oro6.png'),
# 	('7', '1', '11', 'oro7.png'),
# 	('10', '1', '5', 'oro10.png'),
# 	('11', '1', '6', 'oro11.png'),
# 	('12', '1', '7', 'oro12.png'),
# 	('1', '2', '14', 'espada1.png'),
# 	('2', '2', '9', 'espada2.png'),
# 	('3', '2', '10', 'espada3.png'),
# 	('4', '2', '1', 'espada4.png'),
# 	('5', '2', '2', 'espada5.png'),
# 	('6', '2', '3', 'espada6.png'),
# 	('7', '2', '12', 'espada7.png'),
# 	('10', '2', '5', 'espada10.png'),
# 	('11', '2', '6', 'espada11.png'),
# 	('12', '2', '7', 'espada12.png'),
# 	('1', '3', '8', 'copa1.png'),
# 	('2', '3', '9', 'copa2.png'),
# 	('3', '3', '10', 'copa3.png'),
# 	('4', '3', '1', 'copa4.png'),
# 	('5', '3', '2', 'copa5.png'),
# 	('6', '3', '3', 'copa6.png'),
# 	('7', '3', '4', 'copa7.png'),
# 	('10', '3', '5', 'copa10.png'),
# 	('11', '3', '6', 'copa11.png'),
# 	('12', '3', '7', 'copa12.png'),
# 	('1', '4', '13', 'basto1.png'),
# 	('2', '4', '9', 'basto2.png'),
# 	('3', '4', '10', 'basto3.png'),
# 	('4', '4', '1', 'basto4.png'),
# 	('5', '4', '2', 'basto5.png'),
# 	('6', '4', '3', 'basto6.png'),
# 	('7', '4', '4', 'basto7.png'),
# 	('10', '4', '5', 'basto10.png'),
# 	('11', '4', '6', 'basto11.png'),
# 	('12', '4', '7', 'basto12.png');

CREATE TABLE IF NOT EXISTS Evento (
	id INT PRIMARY KEY  auto_increment,
    nombre VARCHAR(100),
    valor int
);
/*
INSERT INTO Evento (nombre, valor) VALUES 
('TRUCO QUIERO', 2),
('TRUCO NO_QUIERO', 1),
('TRUCO IRSE_AL_MAZO', 1),
('TRUCO RETRUCO QUIERO', 3),
('TRUCO RETRUCO NO_QUIERO', 2),
('TRUCO RETRUCO IRSE_AL_MAZO', 2),
('TRUCO RETRUCO VALE_CUATRO QUIERO', 4),
('TRUCO RETRUCO VALE_CUATRO NO_QUIERO', 3),
('TRUCO RETRUCO VALE_CUATRO IRSE_AL_MAZO', 3),
('ENVIDO QUIERO', 2),
('ENVIDO NO_QUIERO', 1),
('ENVIDO IRSE_AL_MAZO', 1),
('REAL_ENVIDO QUIERO', 3),
('REAL_ENVIDO NO_QUIERO', 1),
('REAL_ENVIDO NO_QUIERO', 1),
('FALTA_ENVIDO QUIERO', 111),
('FALTA_ENVIDO NO_QUIERO', 1),
('FALTA_ENVIDO IRSE_AL_MAZO', 1),
('ENVIDO REAL_ENVIDO QUIERO', 5),
('ENVIDO REAL_ENVIDO NO_QUIERO', 2),
('ENVIDO REAL_ENVIDO IRSE_AL_MAZO', 2),
('ENVIDO FALTA_ENVIDO QUIERO', 111),
('ENVIDO FALTA_ENVIDO NO_QUIERO', 2),
('ENVIDO FALTA_ENVIDO IRSE_AL_MAZO', 2),
('REAL_ENVIDO FALTA_ENVIDO QUIERO', 111),
('REAL_ENVIDO FALTA_ENVIDO NO_QUIERO', 3),
('REAL_ENVIDO FALTA_ENVIDO IRSE_AL_MAZO', 3),
('ENVIDO REAL_ENVIDO FALTA_ENVIDO QUIERO', 111),
('ENVIDO REAL_ENVIDO FALTA_ENVIDO NO_QUIERO', 5),
('ENVIDO REAL_ENVIDO FALTA_ENVIDO IRSE_AL_MAZO', 5),
('ENVIDO ENVIDO_ENVIDO QUIERO', 4),
('ENVIDO ENVIDO_ENVIDO NO_QUIERO', 2),
('ENVIDO ENVIDO_ENVIDO IRSE_AL_MAZO', 2),
('ENVIDO ENVIDO_ENVIDO REAL_ENVIDO QUIERO', 7),
('ENVIDO ENVIDO_ENVIDO REAL_ENVIDO NO_QUIERO', 4),
('ENVIDO ENVIDO_ENVIDO REAL_ENVIDO IRSE_AL_MAZO', 4),
('ENVIDO ENVIDO_ENVIDO FALTA_ENVIDO QUIERO', 111),
('ENVIDO ENVIDO_ENVIDO FALTA_ENVIDO NO_QUIERO', 4),
('ENVIDO ENVIDO_ENVIDO FALTA_ENVIDO IRSE_AL_MAZO', 4),
('ENVIDO ENVIDO_ENVIDO REAL_ENVIDO FALTA_ENVIDO QUIERO', 111),
('ENVIDO ENVIDO_ENVIDO REAL_ENVIDO FALTA_ENVIDO NO_QUIERO', 7),
('ENVIDO ENVIDO_ENVIDO REAL_ENVIDO FALTA_ENVIDO IRSE_AL_MAZO', 7),
('IRSE_AL_MAZO', 1);
*/