FROM openjdk:17-jdk-slim-buster

# 앱 JAR 복사
COPY build/libs/server-0.0.1-SNAPSHOT.jar app.jar

# application.yml 복사
COPY src/main/resources/application.yml /config/application.yml

EXPOSE 8080

# Spring이 외부 yml을 인식하게 명시
ENTRYPOINT ["java", "-jar", "/app.jar", "--spring.config.additional-location=file:/config/"]