#
# Build stage
#
FROM maven:3.9.4-amazoncorretto-17 AS build

COPY src /home/app/src
COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package -DskipTests

#
# Package stage
#
FROM amazoncorretto:17

COPY --from=build /home/app/target/*.jar /usr/local/lib/app.jar
ENV JAVA_OPTS="-XX:+UseG1GC -XX:MaxGCPauseMillis=100 -XX:+UseStringDeduplication"
#EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar /usr/local/lib/app.jar