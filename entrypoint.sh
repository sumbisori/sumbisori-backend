#!/bin/sh

if [ "$SPRING_PROFILES_ACTIVE" = "prod" ]; then
  exec java \
    -javaagent:/pinpoint-agent/pinpoint-bootstrap-2.5.3.jar \
    -Dpinpoint.agentId=aws-ec2-1 \
    -Dpinpoint.applicationName=Sumbisori \
    -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE \
    -Duser.timezone=Asia/Seoul \
    -jar /sumbisori.jar
else
  exec java \
    -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE \
    -Duser.timezone=Asia/Seoul \
    -jar /sumbisori.jar
fi
