
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ADD target/todo-todo-jar-with-dependencies.jar todoserver.jar
ADD todo.db todo.db
EXPOSE 8000
ENTRYPOINT exec java $JAVA_OPTS -jar todoserver.jar > /var/log/todoserver.log
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar httpserver.jar
