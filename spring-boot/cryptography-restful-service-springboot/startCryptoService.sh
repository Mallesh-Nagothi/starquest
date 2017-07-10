mvn clean install
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=7999 -jar ./target/cryptography-rest-service-springboot-1.0.0.jar
