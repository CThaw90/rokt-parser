FROM openjdk:17.0.1

WORKDIR /usr/src/myapp
COPY . .

RUN mkdir -p /app/test-files && ./gradlew build copy

CMD ["java", "-jar", "rokt-parser.jar"]