mvn clean install
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000 -jar ./target/sq-registration-ctrls-1.0.0.jar
