FROM openjdk:17-jdk

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} sumbisori.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/sumbisori.jar"]

EXPOSE 8080
