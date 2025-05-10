FROM maven:3.8-openjdk-8 AS builder
WORKDIR /app
COPY pom.xml .
COPY src/main/java/org/mitit/keu/enduser/EUSignJava.jar ./EUSignJava.jar

RUN mvn install:install-file -Dfile=EUSignJava.jar -DgroupId=com.iit -DartifactId=enduser -Dversion=1.0.0 -Dpackaging=jar

COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:8-jre
WORKDIR /app

COPY --from=builder /app/target/keu_diploma-1.0-SNAPSHOT.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]