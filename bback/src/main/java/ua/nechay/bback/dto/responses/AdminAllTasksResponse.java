package ua.nechay.bback.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.domain.TaskModel;
import ua.nechay.bback.dto.AdminShortFormTaskTO;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.base.ResponseTO;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author anechaev
 * @since 04.05.2022
 */
public class AdminAllTasksResponse implements ResponseTO {

    @JsonProperty private final List<AdminShortFormTaskTO> tasks;
    @JsonProperty private final int countOfPages;
    @JsonProperty private final int pageSize;

    public AdminAllTasksResponse(@Nonnull List<AdminShortFormTaskTO> tasks, int countOfPages, int pageSize) {
        this.tasks = tasks;
        this.countOfPages = countOfPages;
        this.pageSize = pageSize;
    }

    public static GenericResponse<AdminAllTasksResponse, GeneralResponseException>
    createGenericResponse(@Nonnull Collection<TaskModel> tasks, int countOfPages, int pageSize) {
        return GenericResponse.fromResponse(
            new AdminAllTasksResponse(
                tasks.stream()
                    .map(AdminShortFormTaskTO::new)
                    .collect(Collectors.toList()),
                countOfPages,
                pageSize
            )
        );
    }
}
