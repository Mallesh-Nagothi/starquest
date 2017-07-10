mvn clean install
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=7998 -jar ./target/kie-usermgmt-rest-service-springboot-1.0.0.jar
