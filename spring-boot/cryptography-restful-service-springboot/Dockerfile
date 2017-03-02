FROM java:8
VOLUME /tmp
ADD ./target/cryptography-rest-service-springboot-0.0.1-SNAPSHOT.jar startq-crypt-srvc-sprngboot-app.jar
RUN sh -c 'touch /startq-crypt-srvc-sprngboot-app.jar'
ENV JAVA_OPS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /startq-crypt-srvc-sprngboot-app.jar" ]

