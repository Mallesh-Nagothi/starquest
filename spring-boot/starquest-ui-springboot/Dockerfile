FROM java:8
VOLUME /tmp
ADD ./target/usermgmt-rest-service-springboot-0.0.1-SNAPSHOT.jar startq-usermgmt-srvc-sprngboot-app.jar
RUN sh -c 'touch /startq-usermgmt-srvc-sprngboot-app.jar'
ENV JAVA_OPS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /startq-usermgmt-srvc-sprngboot-app.jar" ]

