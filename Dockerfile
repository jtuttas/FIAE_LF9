
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ADD target/httpServer-1.0-SNAPSHOT.jar httpserver.jar
EXPOSE 8000
ENTRYPOINT exec java $JAVA_OPTS -jar httpserver.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar httpserver.jar
