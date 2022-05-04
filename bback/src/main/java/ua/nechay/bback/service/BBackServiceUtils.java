package ua.nechay.bback.service;

/**
 * @author anechaev
 * @since 04.05.2022
 */
public final class BBackServiceUtils {

    private BBackServiceUtils() {

    }

    public static int getOffsetForRepo(int page, int size) {
        return page * size;
    }
}
