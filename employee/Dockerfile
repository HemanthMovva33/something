FROM openjdk:11
VOLUME /tmp
EXPOSE 8100
COPY target/employee-service.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security-egd=file:/dev/./urandom","-jar","/app.jar"]