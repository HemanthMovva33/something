FROM openjdk:11
EXPOSE 8081
VOLUME /tmp
COPY target/ui-portal.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security-egd=file:/dev/./urandom","-jar","/app.jar"]