package ua.nechay.bback.dto.responses;

import ua.nechay.bback.dto.base.ResponseExceptionTO;

/**
 * @author anechaev
 * @since 19.04.2022
 */
public enum RegistrationException implements ResponseExceptionTO {
    USER_WITH_DUPLICATED_LOGIN;
}
