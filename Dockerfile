FROM amazoncorretto:17-alpine-jdk

COPY target/security-0.0.1-SNAPSHOT.jar /api-v1.jar

ENTRYPOINT ["java", "-jar", "/api-v1.jar"]
