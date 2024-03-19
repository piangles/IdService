FROM eclipse-temurin:17-jre-alpine
WORKDIR /
ADD ./target/IdService.jar IdService.jar
ENTRYPOINT ["java", "-Dprocess.name=IdService", "-jar", "IdService.jar"]
