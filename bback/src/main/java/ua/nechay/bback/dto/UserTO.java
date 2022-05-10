package ua.nechay.bback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.domain.user.UserModel;
import ua.nechay.bback.domain.user.UserRoleModel;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author anechaev
 * @since 11.05.2022
 */
public class UserTO {

    @JsonProperty private final String login;
    @JsonProperty private final List<String> roles;

    public UserTO(String login, List<String> roles) {
        this.login = login;
        this.roles = roles;
    }

    public UserTO(@Nonnull UserModel user) {
        this.login = user.getLogin();
        this.roles = user.getRoleModels()
            .stream()
            .map(UserRoleModel::getRole)
            .map(Enum::name)
            .collect(Collectors.toList());
    }

    public String getLogin() {
        return login;
    }

    public List<String> getRoles() {
        return roles;
    }
}
