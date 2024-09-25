FROM openjdk:17-jdk-slim

WORKDIR /app

COPY ./build/libs/band_authentication-0.1.jar /app/band_authentication-0.1.jar

CMD ["java", "-jar", "band_authentication-0.1.jar"]