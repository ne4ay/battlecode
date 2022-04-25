package ua.nechay.bback.dto.responses;

import ua.nechay.bback.dto.base.ResponsePlainExceptionTO;

/**
 * @author anechaev
 * @since 19.04.2022
 */
public enum RegistrationResponseException implements ResponsePlainExceptionTO {
    USER_WITH_DUPLICATED_LOGIN;
}
