[![Deploy](https://stash.zooplus.de/projects/CONT/avatar.png?s=96)](https://stash.zooplus.de/projects/CONT/repos/uxt-side-banner/browse)

# Pixabay proof of concept
this is the project to host the functionality to the banner component

> mvn compile package

> mvn docker:build

> mvn spring-boot:run

# Remote debug

> mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"

# References

Building Java Projects with Maven
https://spring.io/guides/gs/maven/

Building a RESTful Web Service
https://spring.io/guides/gs/rest-service/

Swagger
http://uxt-side-banner.dev.zooplus.net/swagger-ui.html

