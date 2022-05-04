FROM openjdk:8-jdk-slim
COPY "./target/banking-credit-service-0.1.jar" "credit-service.jar"
ENTRYPOINT ["java","-jar","credit-service.jar"]