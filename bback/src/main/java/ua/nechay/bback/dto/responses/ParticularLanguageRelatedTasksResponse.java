package ua.nechay.bback.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.base.ResponseTO;

import java.util.List;

/**
 * @author anechaev
 * @since 02.05.2022
 */
public class ParticularLanguageRelatedTasksResponse implements ResponseTO {

    @JsonProperty private final List<ShortFormTaskTO> tasks;

    public ParticularLanguageRelatedTasksResponse(List<ShortFormTaskTO> tasks) {
        this.tasks = tasks;
    }

    public static GenericResponse<ParticularLanguageRelatedTasksResponse, GeneralResponseException>
    createGenericResponse(List<ShortFormTaskTO> tasks)
    {
        return GenericResponse.fromResponse(
            new ParticularLanguageRelatedTasksResponse(tasks)
        );
    }
}
