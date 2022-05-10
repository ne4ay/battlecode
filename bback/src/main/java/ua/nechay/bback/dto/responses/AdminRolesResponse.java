package ua.nechay.bback.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.domain.user.BBackUserRole;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.base.ResponseTO;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;

/**
 * @author anechaev
 * @since 11.05.2022
 */
public class AdminRolesResponse implements ResponseTO {

    @JsonProperty private final List<String> roles;

    public AdminRolesResponse(@Nonnull List<String> roles) {
        this.roles = roles;
    }

    public static GenericResponse<AdminRolesResponse, GeneralResponseException> createGenericResponse(@Nonnull Collection<BBackUserRole> roles) {
        return GenericResponse.fromResponse(
            new AdminRolesResponse(roles.stream()
                .map(Enum::name)
                .toList()
            )
        );
    }
}
