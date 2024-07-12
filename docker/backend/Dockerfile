# Build stage
FROM maven:3.8.7-openjdk-18 AS build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM amazoncorretto:17
ARG PROFILE=dev
ARG APP_VERSION=1.0.0

WORKDIR /app
COPY --from=build /build/target/*.jar /app/

EXPOSE 8088
ENV DB_URL=jdbc:postgresql://postgres-sql-bsn:5432/e-commerce-application
ENV JAR_VERSION=${APP_VERSION}
ENV ACTIVE_PROFILE=${PROFILE}

CMD ["java", "-jar", "-Dspring.profiles.active=dev", "-Dspring.datasource.url=jdbc:postgresql://postgres-sql-bsn:5432/e-commerce-application", "/app/EcommerceProjectBackEnd-1.0.0.jar"]
