FROM amazoncorretto:11-alpine-jdk
COPY target/*.jar producer_app.jar
ENTRYPOINT ["java","-jar","/producer_app.jar"]