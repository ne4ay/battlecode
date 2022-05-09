package ua.nechay.bback.checkers;

import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ua.nechay.bback.config.BBackProperties;

import javax.annotation.Nonnull;

/**
 * @author anechaev
 * @since 06.05.2022
 */
@Component
public class BBackRestEndpointTaskCheckerFactory implements TaskCheckerFactory {

    private final BBackProperties properties;

    public BBackRestEndpointTaskCheckerFactory(@Nonnull BBackProperties properties) {
        this.properties = properties;
    }

    @Override
    public TaskChecker createTaskChecker() {
        SimpleClientHttpRequestFactory httpRequestFactory = new SimpleClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(properties.getRestTaskCheckerConnectTimeoutMs());
        httpRequestFactory.setReadTimeout(properties.getRestTaskCheckerReadTimeoutMs());
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        String checkerBaseUrl = properties.getCheckerBaseUrl();
        String checkerUrlPath = properties.getCheckerUrlPath();
        return new BBackRestEndpointTaskChecker(restTemplate, checkerBaseUrl, checkerUrlPath);
    }
}
