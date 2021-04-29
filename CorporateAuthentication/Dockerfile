FROM openjdk:11
EXPOSE 9005
VOLUME /tmp
COPY target/authentication-service.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security-egd=file:/dev/./urandom","-jar","/app.jar"]