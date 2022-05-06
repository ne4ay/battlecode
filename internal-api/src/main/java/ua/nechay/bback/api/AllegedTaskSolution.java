package ua.nechay.bback.api;

/**
 * @author anechaev
 * @since 06.05.2022
 */
public interface AllegedTaskSolution extends TaskSolution {

    String getProgramText();

    ProgrammingLanguage getProgrammingLanguage();
}
