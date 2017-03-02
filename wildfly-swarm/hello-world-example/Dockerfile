FROM java:alpine
VOLUME /tmp
ADD ./target/gs-rest-service-swarm.jar app-yswarm.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app-yswarm.jar" ]

