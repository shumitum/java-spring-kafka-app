FROM amazoncorretto:11-alpine-jdk
COPY target/*.jar cons_app.jar
ENTRYPOINT ["java","-jar","/cons_app.jar"]