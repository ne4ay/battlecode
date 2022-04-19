package ua.nechay.bback.dto.requests;

import javax.annotation.Nonnull;

/**
 * @author anechaev
 * @since 20.04.2022
 */
public class RegistrationRequest {
    private final String login;
    private final String password;
    private final String email;

    public RegistrationRequest(@Nonnull String login, @Nonnull String password, @Nonnull String eMail) {
        this.login = login;
        this.password = password;
        this.email = eMail;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

}
