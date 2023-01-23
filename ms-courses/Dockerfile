FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/ms-courses

COPY ./pom.xml /app
COPY ./ms-courses/.mvn ./.mvn
COPY ./ms-courses/mvnw .
COPY ./ms-courses/pom.xml .

RUN sed -i 's/\r$//' mvnw
RUN ./mvnw clean package -DskipTests -Dmaven.main.skip -Dspring-boot.repackage.skip
#RUN ./mvnw dependency:go-offline
COPY ./ms-users/src ./src
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /app/ms-courses/target/ms-users-1.0.0.jar ./ms-courses.jar
ENTRYPOINT ["java","-jar","ms-courses.jar"]