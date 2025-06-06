FROM openjdk:17-jdk-alpine3.14

ARG JAR_FILE=build/libs/*.jar

# JAR 및 pinpoint agent 복사
COPY ${JAR_FILE} sumbisori.jar
COPY pinpoint-agent /pinpoint-agent
COPY entrypoint.sh /entrypoint.sh

# 실행 권한 부여
RUN chmod +x /entrypoint.sh

# 진입점 지정
ENTRYPOINT ["/entrypoint.sh"]

EXPOSE 8080
