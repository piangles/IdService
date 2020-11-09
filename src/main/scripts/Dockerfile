FROM java:8
WORKDIR /
ADD IdService.jar IdService.jar
ENTRYPOINT ["java", "-Dprocess.name=IdService", "-jar", "IdService.jar"]
