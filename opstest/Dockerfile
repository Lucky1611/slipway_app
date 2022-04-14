FROM maven:3.6.3-jdk-11-slim
WORKDIR /app
ARG AZ
COPY . .
ENV AZ=${AZ}
EXPOSE 8080
ENTRYPOINT ./mvnw spring-boot:run
