package ua.nechay.bback.dto.base;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author anechaev
 * @since 19.04.2022
 */
public class GenericResponse<R extends ResponseTO, E extends ResponseExceptionTO> {

    @JsonProperty private final R response;
    @JsonProperty private final E exception;

    public GenericResponse(@Nonnull R response, @Nullable E exception) {
        this.response = response;
        this.exception = exception;
    }
}
