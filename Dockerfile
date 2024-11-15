FROM openjdk:17-jdk

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} sumbisori.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","-Duser.timezone=Asia/Seoul","/sumbisori.jar"]

EXPOSE 8080
