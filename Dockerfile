FROM openjdk:17-jdk

VOLUME /tmp

EXPOSE 8083

ARG JAR_FILE=target/accessing-data-mongodb-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]