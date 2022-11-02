FROM openjdk:17
EXPOSE 8088
ARG JAR_FILE=./target/Product-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]