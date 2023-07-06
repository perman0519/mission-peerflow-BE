#FROM openjdk:11
#ARG JAR_FILE=build/libs/*.jar
#COPY ${JAR_FILE} ./app.jar
#ENV TZ=Asia/Seoul
#ENTRYPOINT ["java","-jar","app.jar"]
FROM openjdk:11 as builder

WORKDIR /app

COPY build.gradle settings.gradle ./
COPY gradle ./gradle
COPY gradlew ./gradlew
COPY src ./src

RUN chmod +x ./gradlew
RUN ./gradlew clean build

FROM openjdk:11

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar ./app.jar

ENV TZ=Asia/Seoul

ENTRYPOINT ["java", "-jar", "app.jar"]