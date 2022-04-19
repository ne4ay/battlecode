package ua.nechay.bback.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nonnull;

/**
 * @author anechaev
 * @since 20.04.2022
 */

public class LoginRequest {
    @JsonProperty private final String login;
    @JsonProperty private final String password;

    public LoginRequest(@Nonnull String login, @Nonnull String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
