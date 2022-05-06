package ua.nechay.bback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ua.nechay.bback.checkers.BBackRestEndpointTaskCheckerFactory;
import ua.nechay.bback.checkers.TaskCheckerFactory;

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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new BBackAuthenticationProvider();
    }

    @Bean
    public TaskCheckerFactory taskCheckerFactory() {
        return new BBackRestEndpointTaskCheckerFactory(properties());
    }
}
