package ua.nechay.bback.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.base.ResponseTO;

import javax.annotation.Nullable;

/**
 * @author anechaev
 * @since 19.04.2022
 */
public class RegistrationResponse implements ResponseTO {

    @JsonProperty private final boolean isSuccessful;

    public RegistrationResponse(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public static GenericResponse<RegistrationResponse, RegistrationException> createGenericResponse(
        boolean isSuccessful, @Nullable RegistrationException exception)
    {
        return new GenericResponse<>(
            new RegistrationResponse(isSuccessful),
            exception);
    }
}
