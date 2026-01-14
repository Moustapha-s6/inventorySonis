# Build stage

FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY . /app/
RUN mvn clean package -DskipTests

# Package stage

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/inventorySonis-api.jar inventorySonis-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","inventorySonis-api.jar"]
