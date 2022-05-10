package ua.nechay.bback.domain;

import ua.nechay.bback.api.ProgrammingLanguage;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public enum BBackLanguage implements ProgrammingLanguage {
    JAVA("Java", "JAVA_REST_PORT", 8091),
    PYTHON("Python", "PYTHON_REST_PORT", 8092);

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

    public static List<BBackLanguage> fromNamesCollection(@Nonnull Collection<String> names) {
        return names.stream()
            .map(BBackLanguage::fromName)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .distinct()
            .toList();
    }
}
