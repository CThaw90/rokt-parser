FROM openjdk:11.0

WORKDIR /usr/src/myapp
COPY . .

RUN mkdir -p /app/test-files && ./gradlew build copy

CMD ["java", "-jar", "rokt-parser.jar"]