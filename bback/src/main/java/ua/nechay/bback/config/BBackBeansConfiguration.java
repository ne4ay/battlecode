package ua.nechay.bback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new BBackWebMvcConfigurer();
    }
}
