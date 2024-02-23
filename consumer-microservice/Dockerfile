FROM amazoncorretto:11-alpine-jdk
COPY target/*.jar consumer_app.jar
ENTRYPOINT ["java","-jar","/consumer_app.jar"]