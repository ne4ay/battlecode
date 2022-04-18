package ua.nechay.bback.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

/**
 * @author anechaev
 * @since 15.04.2022
 */
public class BBackProperties {
    private static final Logger log = LoggerFactory.getLogger(BBackProperties.class);

    @Value("${server.port}")
    private int port;

    @PostConstruct
    private void initialize() {
        log.info("SpectraBBackProperties: {}", this);
    }

    @Override
    public String toString() {
        return "BBackProperties{" +
            "port=" + port +
            '}';
    }
}
