# Truco Argentico

Truco argentino es una pagina web que permite a los usuarios crear  y unirse a partidas de truco de 2 jugadores. 

Al principio de la partida se decide por sorteo automático el jugador que es mano, es decir, el que empieza a jugar. En la siguiente ronda (si la hay) empezaría el jugador situado a la derecha del actual.
En cada ronda, se reparten 3 cartas a cada jugador y todos tiran una carta por turno. El jugador con la carta de mayor valor ganará dicha mano y tirará primero en la siguiente (en caso de empate el jugador anterior será el primero en tirar). La primera pareja o jugador que gana 2 manos, gana la ronda.

```
Estado: en curso ...
```
![Logo](/presentacion/partida.png)


## Indice
* [Funcionalidades principales](#funcionalidades-principales)
* [Objetivo](#objetivo)
* [Screenshots](#screenshots)
* [Lenguajes](#-Lenguajes)
* [Como ejecutar](#como-ejecutar)
* [Tecnologias](#tecnologias)

## Funcionalidades principales
- Usuario: Iniciar sesión, registrarse, historial de partidas y progreso.
- Crear una sala (la partida se inicia cuando hay 2 jugadores).
- Unirse a una sala ya creada.
- Repartir: Al iniciar cada ronda deberán repartirse tres cartas a cada jugador
- Orden jugada: Los jugadores deberán seguir un orden al iniciar una ronda y en las tres manos que se juegan en ella (sentido de las agujas del reloj).
- Ganador mano: Gana el equipo que tire la carta de mas valor
- Ganador ronda: Gana el equipo que gane mas manos
- Ganador partida: Gana el equipo que llegue antes a 30 puntos
- Crear eventos / cantar: Durante cada ronda pueden generarse los siguientes eventos. Cada uno tendrá su valor y requisitos para que el jugador pueda realizarlos. 
                             TRUCO - RETRUCO - VALE CUATRO - ENVIDO - REAL ENVIDO - FALTA ENVIDO
- Limite de tiempo en el turno de cada jugador
- Ver ranckindg de jugadores


## Objetivo
El proyecto se realizó con el objetivo de desarrollar una pagina con el patron MVC (Modelo-Vista-Controlador) con Java y Spring, se utilizo Maven para gestionar las dpendencias e HIbernate para la Base de datos. Tambien se implemento Spring WebSocket para la comunicacion entre ambos jugadores.

## Screenshots
![Logo](/presentacion/partida1.png)
![Logo](/presentacion/partida2.png)

## 🛠 Tecnologias
* Java 11
* Spring 5.2.22.RELEASE
* Thymeleaf 3.0.15.RELEASE
* Embedded Jetty Server 9.4.45.v20220203
* Servlet API 4.0.4
* Bootstrap 5.2.0 (webjars)
* IntelliJ IDEA
* Maven 3.8.6
* Spring Test 5.2.22.RELEASE
* Hamcrest 2.2
* JUnit 5.9
* Hibernate 5.4.24.Final
* Mockito 5.3.1
* Playwright 1.36.0

## Como ejecutar
El proyecto debe clonarse en la carpeta htdocs del xampp.
```
$ git clone https://github.com/rociocrespo200/Preguntados
```
Iniciar servidor
```
$ mvn clean jetty:run
```
Ver la pagina
```
 http://localhost:8080/spring
```

La base de datos se encuentra en la carpeta src\main\resources/crearBaseDeDatos.sql, la conexion a la base esta configurada por defecto en el usuario "root" y contraseña vacia, si se quiere cambiar puede hacerlo desde el archivo src\main\java\com\tallerwebi\config/HibernateConfig.class
```
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //se debe buscar el connection string para el motor que se elija
        dataSource.setUrl("jdbc:mysql://localhost:3306/data");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }
```


## Autores
- Rocio Belen Crespo - https://www.github.com/rociocrespo200

