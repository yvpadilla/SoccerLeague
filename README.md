1 INSTALACION

1.1 REQUERIMIENTOS

1.2.1 Tener instalado MySql Server version 5.7 o superior (version Community)

El puerto asignado para este software es el 3306 (por default).

IMPORTANTE!!
Es necesario que el MySql tenga creada la base de datos: 
soccerleague

1.2.2 JDK 1.8 

Esta version es libre y no tiene limitantes en funcionalidad, las versiones posteriores son recortadas en
funcionalidad, por ello es mejor restringirse a la JDK 1.8 en este punto

1.2 CARGA DE DATOS INICIAL

El aplicativo crea las tablas e inicializa datos para prueba, es parte de su procedimiento inicial.

1.3 PROCEDIMIENTO

En la carpeta Target, se encuentra un JAR cuyo nombre es SoccerLeague-0.0.1-SNAPSHOT.jar

Tanto en LINUX como en WINDOWS, la ejecucion del JAR se efectua de la siguiente manera:

java -Dserver.port=9190 -jar SoccerLeague-0.0.1-SNAPSHOT.jar

Para la presente explicacion utilizaremos el puerto 9190

2 FUNCIONALIDAD

Una vez que el aplicativo esta en ejecucion, se puede testear su funcionalidad, previa autenticacion.

En adelante se asumira que el puerto sobre el cual se ejecuta el aplicativo es el 9190 dentro de nuestra maquina local

A fin de ejecutar las invocaciones a los endpoints sera necesario utilizar una de las siguientes herramientas 
sugeridas:
		DISPONIBILIDAD
		LINUX	WINDOWS	PREINSTALADO
Postman		SI	SI	NO
PowerShell	NO	SI	SI

2.1 ENDPOINT DE AUTENTICACION

A fin de representar los 2 tipos de usuario (pagante y no pagante), se tiene 2 usuarios ya registrados en la base 
de datos con los perfiles requeridos.

El endpoint de autenticacion es el unico endpoint disponible sin restriccion alguna.

Todos los usuarios, cualquiera sea su tipo (pagante o no pagante) debera autenticarse para poder utilizar los demas
enpoints

2.1.1 USUARIO NO PAGANTE (FREE)

El usuario no pagante, tiene la limitacion de que, una vez autenticado, este tiene un minuto donde podra hacer a lo mucho
3 invocaciones a cualquiera de los endpoints. En caso requiera utilizar algun endpoint nuevamente debera de
autenticarse nuevamente, pero las restricciones arriba descritas permaneceran por cada autenticacion.

Para autenticarse como usuario no pagante se efectuara la siguiente invocacion al endpoint de autenticacion

2.1.1.1 REQUEST 

POST http://localhost:9190/authenticate
{
        "user": "free_user",
        "password": "password"    
}

2.1.1.2 RESPONSE

{
    "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmcmVlX3VzZXIiLCJleHAiOjE1OTgzMzU1NTIsImlhdCI6MTU5ODMzNTQ5Mn0.ofpCHlhJDdXS0MQAzD_XPYFB-CBwr06WaOlDuOGU1Lw"
}


En el ejemplo lineas arriba, el dato recibido (tag jwt) sera utilizado en el Header de las posteriores invocaciones a 
otros endpoints

2.1.2 USUARIO PAGANTE 

El usuario pagante, una vez autenticado, podra utilizar cualquier endpoint sin restriccion alguna.

Para autenticarse como usuario pagante se efectuara la siguiente invocacion al endpoint de autenticacion

2.1.2.1 REQUEST 

POST http://localhost:9190/authenticate
{
        "user": "payer_user",
        "password": "password"    
}

2.1.2.2 RESPONSE

{
    "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwYXllcl91c2VyIiwiZXhwIjoxNTk4MzM3MTA3LCJpYXQiOjE1OTgzMzcwNDd9.MLM9zZcIICOsFsn7pwiXRDRMVDFjCGBwKB0hjczJxvA"
}

En el ejemplo lineas arriba, el dato recibido (tag jwt) sera utilizado en el Header de las posteriores invocaciones a 
otros endpoints

2.2 ENDPOINTS DE SERVICIO

A continuacion se pasa a detallar los endpoints de servicio:

2.1.1 Listado de las ligas de fútbol nacional .- Considerando información como: 
      nombre, fecha de inicio, fecha de término, cantidad de fechas a jugarse.

EJEMPLO:

GET http://localhost:9190/leagues

RESULTADO: Se mkuestran los datos: identificador (tag id), nombre (tag name), fecha de inicio (tag beginDate),
	   fecha de termino (tag endDate) y cantidad de fechas a jugarse (tag dateQuantity)

[
    {
        "id": 1,
        "name": "League 1",
        "beginDate": "2020-01-11T05:00:00.000+00:00",
        "endDate": "2020-11-11T05:00:00.000+00:00",
        "dateQuantity": 50
    }
]

2.1.2 Listado de equipos que participarán de una liga en particular 

EJEMPLO: En este caso se lista los equipos cuya liga es 1

GET http://localhost:9190/leagues/1/teams

RESULTADO: Devuelve la lista de equipos solicitada

[
    {
        "id": 1,
        "name": "Team 1"
    },
    {
        "id": 2,
        "name": "Team 2"
    },
    {
        "id": 3,
        "name": "Team 3"
    },
    {
        "id": 4,
        "name": "Team 4"
    }
]


2.1.3 Listado de todas las ligas en que participará un equipo en particular 

EJEMPLO: En este caso el valor 2 (entre teams y leagues) representa el identificador del equipo (team)
	 De tal manera que la lectura del endpoint de derecha a izquierda seria: 
	 "Las ligas del equipo 2"

GET http://localhost:9190/teams/2/leagues

RESULTADO: El endpoint devuelve una lista de las ligas donde participa el equipo 2, en este caso solo es una

[
    {
        "id": 1,
        "name": "League 1",
        "beginDate": "2020-01-11T05:00:00.000+00:00",
        "endDate": "2020-11-11T05:00:00.000+00:00",
        "dateQuantity": 50
    }
]

2.1.4 Listado de todos los jugadores de un equipo	

EJEMPLO: En este caso se desea listar a los jugadores del equipo 1

GET http://localhost:9190/teams/1/players

RESULTADO:

[
    {
        "id": 1,
        "name": "Yuri",
        "surname": "Padilla"
    },
    {
        "id": 2,
        "name": "Martin",
        "surname": "Sedanos"
    },
    {
        "id": 3,
        "name": "Robert",
        "surname": "Gabino"
    },
    {
        "id": 4,
        "name": "Enrique",
        "surname": "Zela"
    },
    {
        "id": 5,
        "name": "Enrique",
        "surname": "Olaya"
    },
    {
        "id": 6,
        "name": "Julio",
        "surname": "Pacheco"
    }
]

2.1.5 Creación de un jugador	

EJEMPLO:

POST http://localhost:9190/players

{
    "name": "Yuri",
    "surname": "Padilla"
}

RESULTADO: Nos devuelve el jugador ingresado, en este caso se le asigno el identificador 7

{
    "id": 7,
    "name": "Yuri",
    "surname": "Padilla"
}

2.1.6 Incorporación de jugador (ya existente) a un equipo

EJEMPLO: Se pretende relacionar al jugador 7 dentro del eqipo 1

PUT http://localhost:9190/teams

{
        "team_id": "1",
        "player_id": "7"    
}

RESULTADO: Devuelve los datos del jugador que fue incorporado 

{
    "id": 7,
    "name": "Yuri",
    "surname": "Padilla"
}