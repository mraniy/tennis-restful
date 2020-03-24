FROM openjdk:11-jre-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} tennis.jar
EXPOSE 9008
ENTRYPOINT ["java","-Dspring.profiles.active=rec","-jar","/tennis.jar"]
#docker build -t mraniy/tennis .
#sudo docker run -it -d -p 80:9008  mraniy/tennis