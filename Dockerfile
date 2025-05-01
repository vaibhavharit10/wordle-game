FROM openjdk:21-jdk-slim
WORKDIR /app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew build
CMD ["java", "-cp", "build/classes/java/main", "wordle.Main"]
