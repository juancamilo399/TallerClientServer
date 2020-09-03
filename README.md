# Lab3

[![CircleCI](https://circleci.com/gh/juancamilo399/TallerClientServer.svg?style=svg)](https://app.circleci.com/pipelines/github/juancamilo399/TallerClientServer)

Application deployed in heroku: [here](https://hidden-caverns-54395.herokuapp.com/index.html)


The goal of this workshop is to write a web server using java that supports multiple requests in a row. The server should return all requested static files, including html pages and images. A web page will be built using javascript to test the server.

## Getting Started

The following instructions will allow you to have a copy of the project and run it on your machine.

### Prerequisites

* [Maven](https://maven.apache.org/) - Dependency Management
* [Java 8](https://www.oracle.com/co/java/technologies/javase/javase-jdk8-downloads.html) -  Development Environment 
* [Git](https://git-scm.com/) - Version Control System

### Installing

1. Clone the repository

```
git clone https://github.com/juancamilo399/TallerClientServer.git
```

2. Compile the projet

```
mvn package
```

3. Executing the program

```
mvn exec:java -D "exec.mainClass"="exec.mainClass"="edu.escuelaing.arep.App"

In your browser: http://localhost:36000/index.html
```

4. Generating the documentation

```
mvn javadoc:javadoc
```

The documentation will be generated in target/site/apidocs/index.html.

## Documentation

View [Documentation](https://juancamilo399.github.io/TallerClientServer/apidocs/)

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Heroku](https://www.heroku.com/platform) - Deploy platform

## Inform

* [View inform](https://github.com/juancamilo399/TallerClientServer/blob/master/Workshop_3.pdf)

## Author

* **Juan Camilo Angel Hernandez** 


## License

This project is under GNU General Public License - see the [LICENSE](LICENSE) file for details.
