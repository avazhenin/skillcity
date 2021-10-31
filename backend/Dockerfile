FROM java:8-jdk-alpine
FROM maven:alpine

# image layer
WORKDIR /app
VOLUME ~/.m2 /root/.m2
ADD pom.xml /app
RUN mvn verify clean --fail-never

# Image layer: with the application
COPY . /app
RUN mvn -v
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests
COPY ./target/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]