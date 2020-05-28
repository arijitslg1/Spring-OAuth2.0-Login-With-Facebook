FROM alpine
RUN apk add openjdk8
ADD ./target/OAuth2.0-0.0.1-SNAPSHOT.jar /
EXPOSE 8030
ENTRYPOINT ["java","-jar","OAuth2.0-0.0.1-SNAPSHOT.jar"]
