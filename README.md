# Highscore API

Server side and REST API written with Spring Framework in Java.
<br/>
Client is written in javascript using JSON, AJAX and JQuery
<br/>
Anyone can register with the system by submitting a player name and in return they receive a unique player id. Anyone can submit a score by sending a valid player id, title of the game and their score. All the scores are stored in the database. Top scores can be requested by providing the title of the game. All scores submitted by a player can be requested by providing a valid player id. Scores can be updated or deleted by providing game title and player id. Players along with all their scores can be deleted with player id.


## Getting Started
Built in eclipse using Maven as build manager. Database



### Prerequisites

Preferable basic Java knowledge |
Well commented


## Deployment

Go to IDE, export as Jar.

## Known issues

Cannot write external client when on localhost:8080
Can be bypassed with proxies or disabling security.
Error: 
```
XMLHttpRequest cannot load http://localhost:8080/topscore/angrybirds/5. No 'Access-Control-Allow-Origin' header is present on the requested resource. Origin 'null' is therefore not allowed access.
```
Moved client to Spring framework and parse with thymeleaf


## Running the tests

JUnit testing. Run as JUnit to test.

### Break down into end to end tests

#### GameControllerRestTests
Test for: delete, update, submit and get game object.
<br/>
The reason for test is to get correct Http status codes and data validation

#### PlayerControllerRestTests
Test for: delete, submit and get player object.
<br/>
The reason for test is to get correct Http status codes and data validation

#### HighscoresApplicationTests
Test for: load controllers
<br/>
The reason for test is to check if controllers are being loaded

#### RepositoryTest
Test for: repository save, delete and update
<br/>
The reason for test is to ensure proper hibernation




## Brief API Documentation

**POST** - */player/register*
<br/>
Register new account to database, receive unique playerId in return
<br/>
Example of usage: 
```
http://example.com/player/register

JSON Body Content: 
{
  "playerName": "YOUR-PLAYER-NAME-HERE"
}

```

**GET** - */player/{playerId}*
<br/>
Returns all scores submitted by player(game title, score and Player object with name
<br/>
Example of usage: 
```
http://example.com/player/YOUR-PLAYER-ID

JSON RETURN CONTENT:
[
{
  "player": {
    "playerName": "YOUR-PLAYER-NAME"
  },
  "gameTitle": "GAME-TITLE",
  "score": 8888
}
]
```

**DELETE** - */player/delete/{playerId}*
<br/>
Deletes Player with all its scores.
<br/>
Example of usage: 
```
http://example.com/player/delete/YOUR-PLAYER-ID

```

**POST** - */game/submitscore*
<br/>
Save new score to database
<br/>
Example of usage: 
```
http://example.com/game/submitscore

JSON Body Content: 
{
  "gameTitle": "GAME-TITLE",
  "score": 8888,
  "playerId": "YOUR-PLAYER-ID"
  
}

```

**PUT** - */game/updatescore*
<br/>
Update existing score in database
<br/>
Example of usage: 
```
http://example.com/game/updatescore

JSON Body Content: 
{
  "gameTitle": "GAME-TITLE",
  "score": 8888,
  "playerId": "YOUR-PLAYER-ID"
  
}

```

**GET** - */game/topscore/{playerName}*
<br/>
Return all scores submitted by player
<br/>
Example of usage: 
```
http://example.com/game/topscore/PLAYER-NAME

JSON RETURN Content: 
[
{
  "player": {
    "playerName": "YOUR-PLAYER-NAME"
  },
  "gameTitle": "GAME-TITLE",
  "score": 8888
}
]

```

**GET** - */game/topscore/{gameTitle}/{amount}*
<br/>
Return all scores for certain game and limit it with amount
<br/>
Example of usage: 
```
http://example.com/game/topscore/GAME-TITLE/10

Will list top 10 games based of score

JSON RETURN Content: 
[
{
  "player": {
    "playerName": "YOUR-PLAYER-NAME"
  },
  "gameTitle": "GAME-TITLE",
  "score": 8888
}
]

```

**DELETE** - */game/{gameTitle}/{playerId}*
<br/>
Deletes Game score by player id and game title
<br/>
Example of usage: 
```
http://example.com/game/GAME-TITLE/YOUR-PLAYER-ID

```


## Authors

* **Steven Tihomirov** - *Initial work* 


## License

This project is licensed under the GPL License.
