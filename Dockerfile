FROM openjdk:11-jdk-slim
COPY target/no-country-0.0.1-SNAPSHOT.jar demo.jar
ENTRYPOINT ["java","-jar","demo.jar"]
