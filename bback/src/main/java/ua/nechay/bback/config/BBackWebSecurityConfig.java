package ua.nechay.bback.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.nechay.bback.controllers.AdminController;
import ua.nechay.bback.controllers.LoginController;
import ua.nechay.bback.controllers.RegistrationController;
import ua.nechay.bback.domain.user.BBackUserRole;
import ua.nechay.bback.service.UserService;

/**
 * @author anechaev
 * @since 17.04.2022
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BBackWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired private UserService userService;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
            .csrf().disable()
//                .ignoringAntMatchers("/register", "/login")
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//            .and()
                .cors()
            .and()
                .authorizeRequests()
                .antMatchers("/",
                    RegistrationController.REGISTER_PATH,
                    LoginController.LOGIN_PATH,
                    LoginController.LOGOUT_PATH)
                .permitAll()
                .antMatchers(AdminController.ADMIN_PATH + "/**")
                .hasRole(BBackUserRole.GLOBAL_ADMINISTRATOR.name())
                .anyRequest().authenticated()
//            .and()
//                .rememberMe()
//                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(1))
//                .key("somethingverysecured")
//                .rememberMeParameter("remember-me")
            .and()
                .httpBasic().disable()
//                .authenticationEntryPoint()
                .formLogin()
                .disable()
                .logout()
                .permitAll();
    }

//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
//        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
//        corsConfiguration.setMaxAge(Duration.ofMinutes(10));
//        source.registerCorsConfiguration("/**", corsConfiguration);
//        return new CorsFilter(source);
//    }
}
