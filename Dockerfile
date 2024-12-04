# Этап сборки с использованием Gradle
FROM gradle:7.6-jdk17 AS build

# Копируем проект и устанавливаем рабочую директорию
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

# Собираем проект с помощью Gradle
RUN gradle build --no-daemon

# Этап выполнения с использованием минимального JDK
FROM openjdk:21

# Создаем директорию для приложения
RUN mkdir /app

# Копируем собранные JAR-файлы из этапа сборки
COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar

# Указываем точку входа для запуска приложения
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
