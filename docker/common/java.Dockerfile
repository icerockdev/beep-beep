FROM gitlab.icerockdev.com:4567/docker/java/openjdk-slim-11:latest

WORKDIR /app

ARG SERVICE
COPY docker/run/entrypoint.sh /app/entrypoint.sh
COPY app-build/build/libs /app/libs
COPY app-build/build/${SERVICE}.jar /app/app.jar

ENTRYPOINT ["/app/entrypoint.sh"]
