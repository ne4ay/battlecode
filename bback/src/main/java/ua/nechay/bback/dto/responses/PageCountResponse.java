package ua.nechay.bback.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.base.ResponseTO;

/**
 * @author anechaev
 * @since 02.05.2022
 */
public class PageCountResponse implements ResponseTO {

    @JsonProperty private final int countOfPages;
    @JsonProperty private final int size;

    public PageCountResponse(int count, int size) {
        this.countOfPages = count;
        this.size = size;
    }

    public static GenericResponse<PageCountResponse, GeneralResponseException> createGenericResponse(int count, int size) {
        return GenericResponse.fromResponse(
            new PageCountResponse(count, size)
        );
    }
}
