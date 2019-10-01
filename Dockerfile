FROM  adoptopenjdk/openjdk11:slim
VOLUME /tmp
ARG URL_ELASTIC
ENV URL_ELASTIC $URL_ELASTIC
COPY ./target/adnmutant-0.0.1.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]