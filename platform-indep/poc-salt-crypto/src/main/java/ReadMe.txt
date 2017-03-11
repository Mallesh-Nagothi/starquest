Follow the following instruction for building native library for
Salt Generation


javac com.poc.usermanagement.SaltGenerator.java
javah -jni javac com.poc.usermanagement.SaltGenerator

--compiling C file for  java implemented native method
gcc -c -Wall -Werror -fpic -I /usr/lib/jvm/jdk1.8.0_121/include/ -I /usr/lib/jvm/jdk1.8.0_121/include/linux SaltGenerator.c

--publishing in shared library
gcc -shared -o libSaltGenerator.so SaltGenerator.o

-- keeping .so library path in env variable
LD_LIBRARY_PATH=/home/mallesh/poc/workspace/usermanagement/src:$LD_LIBRARY_PATH





--------------RUN COMMAND----
mvn package

java -jar target/poc-cryptography-rest-service-springboot-0.0.1-SNAPSHOT.jar

To Change Server Port GOTO
/src/main/resources/application.properties
and change the following
server.port: 9001

or provide the port at run time.
