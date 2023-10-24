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
  activo boolean NOT NULL);

INSERT INTO Usuario (nombre, apellido, email, username, password,rol,activo) VALUES ("Rocio","Crespo","rocio@gmail.com","rocio1234","1234","ADMIN",False);
INSERT INTO Usuario (nombre, apellido, email, username, password,rol,activo) VALUES ("Cintia","Pinaud","cintia@gmail.com","PruebaUser","1234","ADMIN",False);
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
