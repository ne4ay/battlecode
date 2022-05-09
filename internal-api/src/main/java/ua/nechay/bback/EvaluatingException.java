package ua.nechay.bback;

public enum EvaluatingException {
    PROGRAM_CODE_IS_NULL,
    DONT_CONTAIN_PUBLIC_MAIN_CLASS,
    TOO_MANY_PUBLIC_MAIN_CLASS,
    UNKNOWN_LANGUAGE,
    ENTRY_POINT_WAS_NOT_FOUND,
}
