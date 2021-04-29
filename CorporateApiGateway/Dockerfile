FROM openjdk:11
EXPOSE 9090
VOLUME /tmp
COPY target/gateway-service.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java","-Djava.security-egd=file:/dev/./urandom","-jar","/app.jar"]