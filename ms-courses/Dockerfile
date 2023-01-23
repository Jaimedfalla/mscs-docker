ARG MSVC_NAME=ms-courses

FROM openjdk:17-jdk-alpine as builder
ARG MSVC_NAME
WORKDIR /app/$MSVC_NAME

COPY ./pom.xml /app
COPY ./$MSVC_NAME/.mvn ./.mvn
COPY ./$MSVC_NAME/mvnw .
COPY ./$MSVC_NAME/pom.xml .

RUN sed -i 's/\r$//' mvnw
RUN ./mvnw clean package -DskipTests -Dmaven.main.skip -Dspring-boot.repackage.skip
#RUN ./mvnw dependency:go-offline
COPY ./$MSVC_NAME/src ./src
RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

ARG MSVC_NAME
ARG VERSION=1.0.0

WORKDIR /app
RUN mkdir ./logs
ARG TARGET_FOLDER=/app/$MSVC_NAME/target
COPY --from=builder $TARGET_FOLDER/$MSVC_NAME-$VERSION.jar ./app.jar
ENTRYPOINT ["java","-jar","app.jar"]