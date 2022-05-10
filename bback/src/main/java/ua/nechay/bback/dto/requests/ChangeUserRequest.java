package ua.nechay.bback.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.dto.UserTO;

import java.util.List;

/**
 * @author anechaev
 * @since 11.05.2022
 */
public class ChangeUserRequest {

    @JsonProperty private UserTO user;

    public ChangeUserRequest(UserTO user) {
        this.user = user;
    }

    public ChangeUserRequest() {
    }

    public String getLogin() {
        return user.getLogin();
    }

    public List<String> getRoles() {
        return user.getRoles();
    }
}
