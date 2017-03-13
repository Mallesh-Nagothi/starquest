FROM java:8
VOLUME /tmp
ADD ./target/non-mobile-ui-springboot-0.0.1-SNAPSHOT.jar startq-ui-non-mobile-sprngboot-app.jar
RUN sh -c 'touch /startq-ui-non-mobile-sprngboot-app.jar'
ENV JAVA_OPS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /startq-ui-non-mobile-sprngboot-app.jar" ]

