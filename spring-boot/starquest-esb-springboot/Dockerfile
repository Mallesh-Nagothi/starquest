FROM java:8
VOLUME /tmp
ADD ./target/xyz.jar xya.jar
RUN sh -c 'touch /xya.jar'
ENV JAVA_OPS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /xya.jar" ]

