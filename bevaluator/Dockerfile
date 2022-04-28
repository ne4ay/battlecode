FROM openjdk:17
LABEL maintainer="Aleksei Nechaev"

ENV APP_HOME="/bevaluator"
ENV APP_NAME="bevaluator"
ENV REST_PORT=8091
ENV LOG_DIR="$APP_HOME/log"
ENV CONFIG_DIR="$APP_HOME/conf"
ENV LAUNCH_ARGS=""
ENV JAR_FILE="bevaluator-0.0.1-SNAPSHOT.jar"

RUN mkdir -p $LOG_DIR

WORKDIR $APP_HOME
COPY ./target/$JAR_FILE app/$JAR_FILE

EXPOSE $APP_PORT

CMD ["java", "-jar", "app/bevaluator-0.0.1-SNAPSHOT.jar"]