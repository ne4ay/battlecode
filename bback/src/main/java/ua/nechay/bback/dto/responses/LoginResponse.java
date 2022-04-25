package ua.nechay.bback.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.base.ResponseTO;

import javax.annotation.Nonnull;

/**
 * @author anechaev
 * @since 20.04.2022
 */
public class LoginResponse implements ResponseTO {

    @JsonProperty private final boolean isSuccessful;
    @JsonProperty private final String jSessionId;

    public LoginResponse(boolean isSuccessful, @Nonnull String jSessionId) {
        this.isSuccessful = isSuccessful;
        this.jSessionId = jSessionId;
    }

    public static GenericResponse<LoginResponse, LoginResponseException> createGenericResponse(boolean isSuccessful, String jSessionId) {
        return GenericResponse.fromResponse(
            new LoginResponse(isSuccessful, jSessionId)
        );
    }
}
