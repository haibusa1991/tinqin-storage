FROM openjdk:17
LABEL authors="p.simeonov"
EXPOSE 8081
COPY rest/target/tinqin-storage.jar tinqin-storage.jar

ENTRYPOINT ["java", "-jar", "/tinqin-storage.jar"]