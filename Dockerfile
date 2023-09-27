FROM bellsoft/liberica-openjdk-alpine-musl:17
EXPOSE 8080
MAINTAINER avgdima
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
