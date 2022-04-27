package ua.nechay.bback.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.base.ResponseTO;

/**
 * @author anechaev
 * @since 28.04.2022
 */
public class LogoutResponse implements ResponseTO {

    @JsonProperty private final boolean isSuccessful;

    public LogoutResponse(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public static GenericResponse<LogoutResponse, GeneralResponseException> createGenericResponse(boolean isSuccessful) {
        return GenericResponse.fromResponse(
            new LogoutResponse(isSuccessful)
        );
    }
}
