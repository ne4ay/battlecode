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

    private GenericResponse(@Nullable R response, @Nullable E exception) {
        this.response = response;
        this.exception = exception;
    }

    public static <R extends ResponseTO, E extends ResponseExceptionTO> GenericResponse<R, E> fromResponse(@Nonnull R response) {
        return new GenericResponse<>(response, null);
    }

    public static <R extends ResponseTO, E extends ResponseExceptionTO> GenericResponse<R, E> fromException(@Nonnull E exception) {
        return new GenericResponse<>(null, exception);
    }
}
