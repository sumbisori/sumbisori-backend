FROM openjdk:17-jdk-alpine3.14

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} sumbisori.jar

EXPOSE 8080

ENTRYPOINT exec java \
  -javaagent:/pinpoint-agent/pinpoint-bootstrap-2.5.3.jar \
  -Dpinpoint.agentId=${PINPOINT_AGENT_ID} \
  -Dpinpoint.applicationName=${PINPOINT_APP_NAME} \
  -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} \
  -Duser.timezone=Asia/Seoul \
  -jar /sumbisori.jar
