package ua.nechay.bback.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.base.ResponseExceptionTO;
import ua.nechay.bback.dto.base.ResponseTO;

/**
 * @author anechaev
 * @since 04.05.2022
 */
public class SimpleCheckingResponse implements ResponseTO {

    @JsonProperty private final boolean isSuccessful;

    public SimpleCheckingResponse(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public static GenericResponse<SimpleCheckingResponse, GeneralResponseException> createGenericResponse(boolean isSuccessful) {
        return GenericResponse.fromResponse(
            new SimpleCheckingResponse(isSuccessful)
        );
    }
}
