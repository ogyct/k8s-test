FROM bellsoft/liberica-openjdk-alpine-musl:17
EXPOSE 8080
MAINTAINER avgdima
ARG JAR_FILE=target/k8s-test-springboot-0.0.3-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
