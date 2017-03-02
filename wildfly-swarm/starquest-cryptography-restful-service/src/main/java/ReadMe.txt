Follow the following instruction for building native library for
Salt Generation


javac com.starquest.usermanagement.SaltGenerator.java
javah -jni javac com.starquest.usermanagement.SaltGenerator

--compiling C file for  java implemented native method
gcc -c -Wall -Werror -fpic -I /usr/lib/jvm/jdk1.8.0_121/include/ -I /usr/lib/jvm/jdk1.8.0_121/include/linux SaltGenerator.c

--publishing in shared library
gcc -shared -o libSaltGenerator.so SaltGenerator.o

-- keeping .so library path in env variable
LD_LIBRARY_PATH=/home/mallesh/StartQuest/workspace/usermanagement/src:$LD_LIBRARY_PATH
