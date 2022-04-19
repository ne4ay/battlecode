package ua.nechay.bback.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.base.ResponseTO;

import javax.annotation.Nullable;

/**
 * @author anechaev
 * @since 20.04.2022
 */
public class LoginResponse implements ResponseTO {

    @JsonProperty private final boolean isSuccessful;

    public LoginResponse(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public static GenericResponse<LoginResponse, LoginException> createGenericResponse(
        boolean isSuccessful, @Nullable LoginException exception)
    {
        return new GenericResponse<>(
            new LoginResponse(isSuccessful),
            exception);
    }
}
