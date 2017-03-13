FROM java:8
VOLUME /tmp
ADD ./target/kie-usermgmt-rest-service-springboot-0.0.1-SNAPSHOT.jar kieUsrRst-springboot-app.jar
RUN sh -c 'touch /kieUsrRst-springboot-app.jar'
ENV JAVA_OPS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /kieUsrRst-springboot-app.jar" ]

