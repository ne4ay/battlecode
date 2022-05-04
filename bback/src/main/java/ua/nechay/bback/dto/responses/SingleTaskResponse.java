package ua.nechay.bback.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.catalina.connector.Response;
import ua.nechay.bback.domain.TaskModel;
import ua.nechay.bback.dto.TaskTO;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.base.ResponseTO;

import javax.annotation.Nonnull;

/**
 * @author anechaev
 * @since 05.05.2022
 */
public class SingleTaskResponse implements ResponseTO {

    @JsonProperty private final TaskTO task;

    public SingleTaskResponse(TaskTO task) {
        this.task = task;
    }

    public static GenericResponse<SingleTaskResponse, GeneralResponseException> createGenericResponse(@Nonnull TaskModel taskModel) {
        return GenericResponse.fromResponse(
            new SingleTaskResponse(new TaskTO(taskModel))
        );
    }
}
