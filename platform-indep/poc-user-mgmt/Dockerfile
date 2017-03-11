FROM java:8
VOLUME /tmp
ADD ./target/poc-usermgmt-rest-service-springboot-0.0.1-SNAPSHOT.jar poc-usermgmt-srvc-sprngboot-app.jar
RUN sh -c 'touch /poc-usermgmt-srvc-sprngboot-app.jar'
ENV JAVA_OPS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /poc-usermgmt-srvc-sprngboot-app.jar" ]

