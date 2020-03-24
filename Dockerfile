#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/tennis-0.0.1-SNAPSHOT.jar /usr/local/lib/tennis.jar
EXPOSE 9008
ENTRYPOINT ["java","-Dspring.profiles.active=rec","-jar","/usr/local/lib/tennis.jar"]
#docker build -t mraniy/tennis .
#sudo docker run -it -d -p 80:9008  mraniy/tennis