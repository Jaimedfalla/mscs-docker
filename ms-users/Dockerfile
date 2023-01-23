FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/ms-usuarios

COPY ./pom.xml /app
COPY ./ms-users/.mvn ./.mvn
COPY ./ms-users/mvnw .
COPY ./ms-users/pom.xml .

RUN sed -i 's/\r$//' mvnw
RUN ./mvnw clean package -DskipTests -Dmaven.main.skip -Dspring-boot.repackage.skip
#RUN ./mvnw dependency:go-offline
COPY ./ms-users/src ./src
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/ms-usuarios/target/ms-users-1.0.0.jar ./ms-users.jar
ENTRYPOINT ["java","-jar","ms-users.jar"]