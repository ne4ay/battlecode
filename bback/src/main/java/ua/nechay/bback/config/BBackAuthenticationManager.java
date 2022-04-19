package ua.nechay.bback.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author anechaev
 * @since 20.04.2022
 */
@Component
public class BBackAuthenticationManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return authentication;
        }
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
    }
}
