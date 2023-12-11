FROM amazoncorretto:11
#ENV TZ=Europe/Moscow
COPY ${JAR_FILE} apiTaskManagement-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/apiTaskManagement-0.0.1-SNAPSHOT.jar"]
