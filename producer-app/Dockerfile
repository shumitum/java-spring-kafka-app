FROM amazoncorretto:11-alpine-jdk
COPY target/*.jar prod_app.jar
ENTRYPOINT ["java","-jar","/prod_app.jar"]