package ua.nechay.bback.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.domain.user.UserModel;
import ua.nechay.bback.dto.AdminShortFormUserTO;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.base.ResponseTO;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;

/**
 * @author anechaev
 * @since 10.05.2022
 */
public class AdminAllUsersResponse implements ResponseTO {

    @JsonProperty private final List<AdminShortFormUserTO> users;
    @JsonProperty private final int countOfPages;
    @JsonProperty private final int pageSize;

    public AdminAllUsersResponse(List<AdminShortFormUserTO> users, int countOfPages, int pageSize) {
        this.users = users;
        this.countOfPages = countOfPages;
        this.pageSize = pageSize;
    }

    public static GenericResponse<AdminAllUsersResponse, GeneralResponseException>
    createGenericResponse(@Nonnull Collection<UserModel> users, int countOfPages, int pageSize)
    {
        return GenericResponse.fromResponse(
            new AdminAllUsersResponse(
                users.stream()
                    .map(AdminShortFormUserTO::new)
                    .toList(),
                countOfPages,
                pageSize
            )
        );
    }
}
