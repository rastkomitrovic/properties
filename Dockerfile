FROM openjdk:14-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.war
COPY ${JAR_FILE} Properties-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Properties-0.0.1-SNAPSHOT.jar"]