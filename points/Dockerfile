FROM openjdk:11
VOLUME /tmp
EXPOSE 8300
COPY target/points-service.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security-egd=file:/dev/./urandom","-jar","/app.jar"]
