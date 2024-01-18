# Build stage
FROM mcr.microsoft.com/openjdk/jdk:21-mariner AS build
ENV JAR_FILE=containerapps-albumapi-java-0.0.1-SNAPSHOT.jar
WORKDIR /app
COPY mvnw* /app/
COPY .mvn /app/.mvn
COPY pom.xml /app
COPY ./src /app/src
RUN ls -la /app
RUN ./mvnw dependency:go-offline -B -Dproduction package


# Runtime stage
FROM mcr.microsoft.com/openjdk/jdk:21-mariner
COPY --from=build /app/target/containerapps-albumapi-java-0.0.1-SNAPSHOT.jar /usr/src/myapp/
EXPOSE 8080
CMD ["/usr/bin/java", "-jar", "/usr/src/myapp/containerapps-albumapi-java-0.0.1-SNAPSHOT.jar"]