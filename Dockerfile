FROM java:8
VOLUME /tmp
LABEL version="1.0"
ADD target/demo-0.0.1-SNAPSHOT.jar.jar demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]