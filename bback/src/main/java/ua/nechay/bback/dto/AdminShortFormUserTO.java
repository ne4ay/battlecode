package ua.nechay.bback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.domain.user.UserModel;
import ua.nechay.bback.domain.user.UserRoleModel;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author anechaev
 * @since 10.05.2022
 */
public class AdminShortFormUserTO {

    @JsonProperty private final long userId;
    @JsonProperty private final String login;
    @JsonProperty private final int experience;
    @JsonProperty private final int level;
    @JsonProperty private final List<String> roles;

    public AdminShortFormUserTO(long userId, String login, int experience, int level, @Nonnull List<String> roles) {
        this.userId = userId;
        this.login = login;
        this.experience = experience;
        this.level = level;
        this.roles = roles;
    }

    public AdminShortFormUserTO(@Nonnull UserModel user) {
        this.userId = user.getId();
        this.login = user.getLogin();
        this.experience = user.getExperience();
        this.level = user.getLevel();
        this.roles = user.getRoleModels()
            .stream()
            .map(UserRoleModel::getRole)
            .map(Enum::name)
            .collect(Collectors.toList());
    }
}
