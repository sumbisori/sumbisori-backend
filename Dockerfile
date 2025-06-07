FROM openjdk:17-jdk

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} sumbisori.jar

ENTRYPOINT ["java","-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}","-Duser.timezone=Asia/Seoul","-jar","/sumbisori.jar"]

EXPOSE 8080
