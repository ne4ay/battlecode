FROM openjdk:17
LABEL maintainer="Aleksei Nechaev"

ENV APP_HOME="/bcback"
ENV APP_NAME="bback"
ENV LOG_DIR="$APP_HOME/log"
ENV CONFIG_DIR="$APP_HOME/conf"
ENV LAUNCH_ARGS=""
ENV JAR_FILE="bback-0.0.1-SNAPSHOT.jar"

RUN mkdir -p $LOG_DIR

WORKDIR $APP_HOME
COPY ./target/$JAR_FILE app/$JAR_FILE

EXPOSE $APP_PORT

CMD ["java", "-jar", "app/bback-0.0.1-SNAPSHOT.jar", "--server.port=8090"]