FROM openjdk:11-jdk-slim
COPY --from=build /target/no-country-0.0.1-SNAPSHOT.jar demo.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]
