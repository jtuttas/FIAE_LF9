FROM openjdk:8-jdk-alpine

ENTRYPOINT exec java $JAVA_OPTS -jar todoserver.jar > /var/log/todoserver.log
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar httpserver.jar
