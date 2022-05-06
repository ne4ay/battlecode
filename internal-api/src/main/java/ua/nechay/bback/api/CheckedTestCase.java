package ua.nechay.bback.api;


/**
 * @author anechaev
 * @since 06.05.2022
 */
public interface CheckedTestCase extends TestCase {

    String getActualOutput();

    boolean isCorrect();
}
