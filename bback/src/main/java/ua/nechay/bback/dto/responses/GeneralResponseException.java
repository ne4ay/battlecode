package ua.nechay.bback.dto.responses;

import ua.nechay.bback.dto.base.ResponsePlainExceptionTO;

/**
 * @author anechaev
 * @since 24.04.2022
 */
public enum GeneralResponseException implements ResponsePlainExceptionTO {
    NOT_ENOUGH_PERMISSIONS,
    UNAUTHORIZED,
    SERVER_ERROR,
    WRONG_REQUEST,
    WRONG_CREDENTIALS,
    USER_WITH_DUPLICATED_LOGIN,
    NOT_FOUND,
    TIMEOUT,
}
