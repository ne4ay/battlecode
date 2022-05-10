package ua.nechay.bback.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CollectionId;
import ua.nechay.bback.domain.user.UserModel;
import ua.nechay.bback.dto.ShortFormUserTO;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.base.ResponseTO;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;

/**
 * @author anechaev
 * @since 10.05.2022
 */
public class RatingUsersResponse implements ResponseTO {

    @JsonProperty private final List<ShortFormUserTO> users;
    @JsonProperty private final int countOfPages;
    @JsonProperty private final int pageSize;

    public RatingUsersResponse(List<ShortFormUserTO> users, int countOfPages, int pageSize) {
        this.users = users;
        this.countOfPages = countOfPages;
        this.pageSize = pageSize;
    }

    public static GenericResponse<RatingUsersResponse, GeneralResponseException>
    createGenericResponse(@Nonnull Collection<UserModel> users, int countOfPages, int pageSize)
    {
        return GenericResponse.fromResponse(
            new RatingUsersResponse(
                users.stream()
                    .map(ShortFormUserTO::new)
                    .toList(),
                countOfPages,
                pageSize
            )
        );
    }
}
