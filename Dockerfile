FROM amazoncorretto:8
WORKDIR /
ADD ./target/IdService.jar IdService.jar
ENTRYPOINT ["java", "-Dprocess.name=IdService", "-jar", "IdService.jar"]
