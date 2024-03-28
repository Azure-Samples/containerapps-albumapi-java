FROM mcr.microsoft.com/openjdk/jdk:21-mariner
COPY target/containerapps-albumapi-java-0.0.1-SNAPSHOT.jar /usr/src/myapp/
EXPOSE 8080
CMD ["/usr/bin/java", "-jar", "/usr/src/myapp/containerapps-albumapi-java-0.0.1-SNAPSHOT.jar"]
