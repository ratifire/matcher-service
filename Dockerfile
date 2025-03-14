FROM gradle:5.6.2-jdk21 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM FROM openjdk:21

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/ /app/

ENTRYPOINT ["java","-jar","/app/kotlin-docker-gradle-app.jar"]
