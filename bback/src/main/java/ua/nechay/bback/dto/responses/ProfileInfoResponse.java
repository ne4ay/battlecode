package ua.nechay.bback.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.domain.user.BBackUserRole;
import ua.nechay.bback.domain.user.UserModel;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.base.ResponseTO;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @since 24.04.2022
 */
public class ProfileInfoResponse implements ResponseTO {

    @JsonProperty private final String login;
    @JsonProperty private final int experience;
    @JsonProperty private final int level;
    @JsonProperty private final List<BBackUserRole> roles;

    public ProfileInfoResponse(@Nonnull String login, int experience, int level, @Nonnull List<BBackUserRole> roles) {
        this.login = login;
        this.experience = experience;
        this.level = level;
        this.roles = roles;
    }

    public static GenericResponse<ProfileInfoResponse, GeneralResponseException> createGenericResponse(@Nonnull UserModel userModel) {
        return GenericResponse.fromResponse(
            new ProfileInfoResponse(
                userModel.getUsername(),
                userModel.getExperience(),
                userModel.getLevel(),
                new ArrayList<>(userModel.getRoles())));
    }
}
