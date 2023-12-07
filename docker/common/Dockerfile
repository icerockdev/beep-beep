FROM gitlab.icerockdev.com:4567/docker/java/openjdk-slim-11:latest

WORKDIR /app

COPY docker/run/entrypoint.sh /app/entrypoint.sh
COPY app-build/build/app.jar /app/app.jar

ENTRYPOINT ["/app/entrypoint.sh"]
