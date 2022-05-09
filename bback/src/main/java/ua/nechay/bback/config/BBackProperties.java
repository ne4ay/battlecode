package ua.nechay.bback.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

/**
 * @author anechaev
 * @since 15.04.2022
 */
public class BBackProperties {
    private static final Logger log = LoggerFactory.getLogger(BBackProperties.class);

    @Value("${server.port}")
    private int port;

    @Value("${rest.task-checker.api.connect-timeout-ms:20000}")
    private int restTaskCheckerConnectTimeoutMs;

    @Value("${rest.task-checker.api.read-timeout-ms:20000}")
    private int restTaskCheckerReadTimeoutMs;

    @Value("${rest.task-checker.base-url}")
    private String checkerBaseUrl;

    @Value("${task-checker.checking-timeout-ms}")
    private int checkingTimeoutMs;

    @Value("${rest.task-checker.url-path}")
    private String checkerUrlPath;

    @PostConstruct
    private void initialize() {
        log.info("SpectraBBackProperties: {}", this);
    }

    public int getPort() {
        return port;
    }

    public int getRestTaskCheckerConnectTimeoutMs() {
        return restTaskCheckerConnectTimeoutMs;
    }

    public int getRestTaskCheckerReadTimeoutMs() {
        return restTaskCheckerReadTimeoutMs;
    }

    public String getCheckerBaseUrl() {
        return checkerBaseUrl;
    }

    public int getCheckingTimeoutMs() {
        return checkingTimeoutMs;
    }

    public String getCheckerUrlPath() {
        return checkerUrlPath;
    }

    @Override
    public String toString() {
        return "BBackProperties{" +
            "port=" + port +
            ", restTaskCheckerConnectTimeoutMs=" + restTaskCheckerConnectTimeoutMs +
            ", restTaskCheckerReadTimeoutMs=" + restTaskCheckerReadTimeoutMs +
            ", checkerBaseUrl='" + checkerBaseUrl + '\'' +
            ", checkingTimeoutMs=" + checkingTimeoutMs +
            ", checkerUrlPath='" + checkerUrlPath + '\'' +
            '}';
    }
}
