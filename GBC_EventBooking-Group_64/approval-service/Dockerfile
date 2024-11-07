#----------------
# BUILD STAGE
#----------------

FROM gradle:7.5.1-jdk17-alpine AS builder

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle build -x test

#----------------
# PACKAGE STAGE
#----------------

FROM openjdk:17-jdk-slim

RUN mkdir /app

COPY --from=builder /home/gradle/src/build/libs/*.jar /app/approval-service.jar

ENV DATABASE_USERNAME=admin \
    DATABASE_PASSWORD=password

EXPOSE 9005

ENTRYPOINT ["java", "-jar",  "/app/approval-service.jar"]