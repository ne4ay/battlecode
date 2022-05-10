package ua.nechay.bback.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.domain.user.UserModel;
import ua.nechay.bback.dto.UserTO;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.base.ResponseTO;

import javax.annotation.Nonnull;

/**
 * @author anechaev
 * @since 11.05.2022
 */
public class SingleUserResponse implements ResponseTO {

    @JsonProperty private final UserTO user;

    public SingleUserResponse(@Nonnull UserTO user) {
        this.user = user;
    }

    public static GenericResponse<SingleUserResponse, GeneralResponseException> createGenericResponse(@Nonnull UserModel user) {
        return GenericResponse.fromResponse(
            new SingleUserResponse(new UserTO(user))
        );
    }
}
