package ua.nechay.bback.domain;

import javax.annotation.Nonnull;
import java.util.Arrays;
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

    @Nonnull
    public String getLanguageName() {
        return name;
    }

    public int getRestPort() {
        return Optional.ofNullable(System.getenv(portEnvVariableName))
            .map(Integer::parseInt)
            .orElse(defaultPort);
    }

    public static Optional<BBackLanguage> fromName(@Nonnull String name) {
        return Arrays.stream(BBackLanguage.values())
            .filter(langName -> langName.getLanguageName().equals(name))
            .findFirst();
    }
}
