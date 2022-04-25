package ua.nechay.bback.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.domain.user.UserModel;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.base.ResponseTO;

import javax.annotation.Nonnull;

/**
 * @author anechaev
 * @since 24.04.2022
 */
public class ProfileInfoResponse implements ResponseTO {

    @JsonProperty private final String login;
    @JsonProperty private final int experience;
    @JsonProperty private final int level;

    public ProfileInfoResponse(@Nonnull String login, int experience, int level) {
        this.login = login;
        this.experience = experience;
        this.level = level;
    }

    public static GenericResponse<ProfileInfoResponse, GeneralResponseException> createGenericResponse(@Nonnull UserModel userModel) {
        return GenericResponse.fromResponse(
            new ProfileInfoResponse(userModel.getUsername(),
                userModel.getExperience(),
                userModel.getLevel()));
    }
}
