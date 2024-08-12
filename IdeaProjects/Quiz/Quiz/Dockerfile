FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/*.jar /app/myapp.jar
EXPOSE 8082
CMD ["java","-jar","/app/myapp.jar"]