package ua.nechay.bback.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.dto.ShortFormTaskTO;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.base.ResponseTO;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author anechaev
 * @since 02.05.2022
 */
public class ParticularLanguageRelatedTasksResponse implements ResponseTO {

    @JsonProperty private final List<ShortFormTaskTO> tasks;
    @JsonProperty private final int countOfPages;
    @JsonProperty private final int pageSize;

    public ParticularLanguageRelatedTasksResponse(@Nonnull List<ShortFormTaskTO> tasks, int countOfPages, int pageSize) {
        this.tasks = tasks;
        this.countOfPages = countOfPages;
        this.pageSize = pageSize;
    }

    public static GenericResponse<ParticularLanguageRelatedTasksResponse, GeneralResponseException>
    createGenericResponse(@Nonnull List<ShortFormTaskTO> tasks, int countOfPages, int pageSize)
    {
        return GenericResponse.fromResponse(
            new ParticularLanguageRelatedTasksResponse(tasks, countOfPages, pageSize)
        );
    }
}
