package ua.nechay.bback.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author anechaev
 * @since 21.04.2022
 */
public class BBackWebMvcConfigurer implements WebMvcConfigurer {

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
