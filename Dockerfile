FROM amazoncorretto:17-alpine-jdk
MAINTAINER boladodacopa.tk
COPY bets.jar bets.jar
ENTRYPOINT ["java","-jar","/bets.jar"]