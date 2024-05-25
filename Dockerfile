FROM openjdk:11
COPY "./target/mb-exchange-service-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]