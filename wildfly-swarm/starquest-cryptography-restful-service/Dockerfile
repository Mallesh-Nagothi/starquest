FROM java:alpine
VOLUME /tmp
ADD ./target/cryptography-rest-service-swarm.jar startq-crypt-yswarm.jar
RUN sh -c 'touch /startq-crypt-yswarm.jar'
ENV JAVA_OPS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /startq-crypt-yswarm.jar" ]

