package ua.nechay.bback.checkers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ua.nechay.bback.api.AllegedTaskSolution;
import ua.nechay.bback.api.CheckedTaskSolution;
import ua.nechay.bback.api.ProgrammingLanguage;
import ua.nechay.bback.domain.BBackLanguage;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * @author anechaev
 * @since 06.05.2022
 */
public class BBackRestEndpointTaskChecker implements RestTaskChecker {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public BBackRestEndpointTaskChecker(@Nonnull RestTemplate restTemplate, @Nonnull String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Override
    public Optional<CheckedTaskSolution> check(AllegedTaskSolution allegedTaskSolution) {
        ProgrammingLanguage language = allegedTaskSolution.getProgrammingLanguage();
        if (!(language instanceof BBackLanguage bBackLanguage)) {
            throw new IllegalStateException("Unknown programming language instance: "  + language);
        }
        String url = baseUrl + bBackLanguage.getRestPort() + "/check";
        ResponseEntity<? extends CheckedTaskSolution> response =
            restTemplate.postForEntity(url, allegedTaskSolution, CheckedTaskSolution.class);
        return response.getStatusCode().is2xxSuccessful()
            ? Optional.ofNullable(response.getBody())
            : Optional.empty();
    }
}
