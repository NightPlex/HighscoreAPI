# Highscore API

Server side and REST API written with Spring Framework in Java. Client is written in javascript using JSON, AJAX and JQuery

## Getting Started



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




## Brief API Documentation

**GET** - */player/register*
Register new account to database, receive unique playerId in return <br/>
Example of usage: 
```
http://example.com/player/register/iamname
```

**GET** - */player/register*
Register new account to database, receive unique playerId in return
Example of usage: 
```
http://example.com/player/register/iamname
```



## Authors

* **Steven Tihomirov** - *Initial work* 


## License

This project is licensed under the MIT License.
