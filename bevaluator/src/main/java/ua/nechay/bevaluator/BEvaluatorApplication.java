package ua.nechay.bevaluator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@SpringBootApplication
public class BEvaluatorApplication {
    private static final String SERVER_PORT_PROPERTY_NAME = "server.port";
    private static final String REST_PORT_ENV_VAR_NAME = "REST_PORT";
    private static final String DEFAULT_PORT = "8091";

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BEvaluatorApplication.class);
        app.setDefaultProperties(determineProperties());
        app.run(args);
    }

    private static Map<String, Object> determineProperties() {
        String port = Optional.ofNullable(System.getenv(REST_PORT_ENV_VAR_NAME))
            .orElse(DEFAULT_PORT);
        return Collections
            .singletonMap(SERVER_PORT_PROPERTY_NAME, port);
    }

}
