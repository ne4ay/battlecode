package ua.nechay.bback.domain;

import java.util.Optional;

public enum BBackLanguage {
    JAVA("Java", "JAVA_REST_PORT", 8091);

    private final String name;
    private final String portEnvVariableName;
    private final int defaultPort;

    BBackLanguage(String name, String portEnvVariableName, int defaultPort) {
        this.name = name;
        this.portEnvVariableName = portEnvVariableName;
        this.defaultPort = defaultPort;
    }

    public String getName() {
        return name;
    }

    public int getRestPort() {
        return Optional.ofNullable(System.getenv(portEnvVariableName))
            .map(Integer::parseInt)
            .orElse(defaultPort);
    }
}
