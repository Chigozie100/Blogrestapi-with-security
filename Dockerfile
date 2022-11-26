FROM openjdk:18
COPY out/artifacts/restfulApi_BlogApp_jar/restfulApi-BlogApp.jar restfulApi-BlogApp.jar
ENTRYPOINT ["java","-jar","restfulApi-BlogApp.jar"]