package ua.nechay.bback.checkers;

import org.checkerframework.checker.nullness.Opt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ua.nechay.bback.BBackCheckedSolution;
import ua.nechay.bback.api.AllegedTaskSolution;
import ua.nechay.bback.api.CheckedTaskSolution;
import ua.nechay.bback.domain.BBackLanguage;

import javax.annotation.Nonnull;
import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author anechaev
 * @since 06.05.2022
 */
public class BBackRestEndpointTaskChecker implements RestTaskChecker {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String urlPath;

    public BBackRestEndpointTaskChecker(@Nonnull RestTemplate restTemplate, @Nonnull String baseUrl, @Nonnull String urlPath) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.urlPath = urlPath;
    }

    @Override
    public CompletableFuture<Optional<CheckedTaskSolution>> check(AllegedTaskSolution allegedTaskSolution) {
        return CompletableFuture.supplyAsync(() -> sendAndGetResponse(allegedTaskSolution));
    }

    private Optional<CheckedTaskSolution> sendAndGetResponse(AllegedTaskSolution allegedTaskSolution) {
        String languageName = allegedTaskSolution.getProgrammingLanguage();
        Optional<BBackLanguage> maybeLanguage = BBackLanguage.fromName(languageName);
        if (maybeLanguage.isEmpty()) {
            throw new IllegalStateException("Unknown programming language instance: " + languageName);
        }
        BBackLanguage language = maybeLanguage.get();
        String url = baseUrl + language.getRestPort() + urlPath;
        ResponseEntity<BBackCheckedSolution> response;
        try {
            response = restTemplate.postForEntity(url, allegedTaskSolution, BBackCheckedSolution.class);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        if (response.getStatusCode().is2xxSuccessful()) {
            return Optional.ofNullable(response.getBody());
        } else {
            return Optional.empty();
        }
    }
}
