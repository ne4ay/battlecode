package ua.nechay.bback.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.base.ResponseTO;

/**
 * @author anechaev
 * @since 04.05.2022
 */
public class AddNewTaskResponse implements ResponseTO {

    @JsonProperty private final boolean isSuccessful;

    public AddNewTaskResponse(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public static GenericResponse<AddNewTaskResponse, GeneralResponseException> createGenericResponse(boolean isSuccessful) {
        return GenericResponse.fromResponse(
            new AddNewTaskResponse(isSuccessful)
        );
    }
}
