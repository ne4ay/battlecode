package ua.nechay.bback.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.domain.user.UserModel;

import javax.annotation.Nonnull;

/**
 * @author anechaev
 * @since 10.05.2022
 */
public class ShortFormUserTO {

    @JsonProperty private final long userId;
    @JsonProperty private final String login;
    @JsonProperty private final int percentValueToNextLevel;
    @JsonProperty private final int experience;
    @JsonProperty private final int level;

    public ShortFormUserTO(long userId, String login, int percentValueToNextLevel, int experience, int level) {
        this.userId = userId;
        this.login = login;
        this.percentValueToNextLevel = percentValueToNextLevel;
        this.experience = experience;
        this.level = level;
    }

    public ShortFormUserTO(@Nonnull UserModel user) {
        this.userId = user.getId();
        this.login = user.getLogin();
        this.percentValueToNextLevel = user.getPercentValueForNextLevel();
        this.experience = user.getExperience();
        this.level = user.getLevel();
    }

}
