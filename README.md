[![Deploy](https://stash.zooplus.de/projects/CONT/avatar.png?s=96)](https://stash.zooplus.de/projects/CONT/repos/uxt-side-banner/browse)

# Springboot load properties

install Maven

> brew install maven

create a maven project

> mvn -B archetype:generate \
  -DarchetypeGroupId=org.apache.maven.archetypes \
  -DgroupId=com.amm \
  -DartifactId=loadproperties

run the next commmands

> mvn clean compile package

> mvn docker:build

> mvn spring-boot:run

> mvn clean package spring-boot:run

# Remote debug

> mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"

# References

Building Java Projects with Maven
https://spring.io/guides/gs/maven/

Building a RESTful Web Service
https://spring.io/guides/gs/rest-service/

Swagger
http://uxt-side-banner.dev.zooplus.net/swagger-ui.html
