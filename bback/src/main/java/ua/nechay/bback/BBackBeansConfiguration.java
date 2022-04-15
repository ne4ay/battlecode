package ua.nechay.bback;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author anechaev
 * @since 15.04.2022
 */
@Configuration
public class BBackBeansConfiguration {

    @Bean
    public BBackProperties properties() {
        return new BBackProperties();
    }

}
