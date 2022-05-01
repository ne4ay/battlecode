package ua.nechay.bback.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.nechay.bback.dto.base.GenericResponse;
import ua.nechay.bback.dto.base.ResponseTO;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author anechaev
 * @since 01.05.2022
 */
public class LanguagesResponse implements ResponseTO {

    @JsonProperty private final List<LanguageTO> langs;

    public LanguagesResponse(@Nonnull List<LanguageTO> langs) {
        this.langs = langs;
    }

    public static GenericResponse<LanguagesResponse, GeneralResponseException> createGenericResponse(List<LanguageTO> langs) {
        return GenericResponse.fromResponse(
            new LanguagesResponse(langs)
        );
    }
}
