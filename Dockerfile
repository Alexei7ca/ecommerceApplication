# Stage 1: Build the application
FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /app

COPY mvnw ./
COPY .mvn ./.mvn

COPY pom.xml ./
COPY src ./src

RUN ./mvnw dependency:go-offline

RUN ./mvnw clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY --from=build /app/target/ecommerceApplication-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]


