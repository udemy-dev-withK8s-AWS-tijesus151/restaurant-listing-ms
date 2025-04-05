#FROM adoptopenjdk/openjdk:17-jdk-slim
#FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-slim
FROM openjdk:17-jdk-slim
WORKDIR /opt
COPY /target/*.jar /opt/app.jar
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar