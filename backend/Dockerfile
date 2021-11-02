FROM java:8-jdk-alpine
FROM maven:alpine

# image layer
WORKDIR /app
ADD pom.xml /app
RUN mvn verify clean --fail-never

# Image layer: with the application
COPY . /app
RUN mvn -v
RUN mvn clean package -DskipTests
RUN cp /app/target/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
