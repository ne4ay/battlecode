package ua.nechay.bevaluator.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nechay.bback.BBackCheckedSolution;
import ua.nechay.bback.BBackTaskSolution;
import ua.nechay.bback.EvaluatingException;
import ua.nechay.bevaluator.BEvaluatorType;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Optional;

/**
 * @author anechaev
 * @since 06.05.2022
 */
@RestController
@RequestMapping("/check")
public class CheckController {


    @PostMapping
    public @ResponseBody BBackCheckedSolution checkSolution(@RequestBody BBackTaskSolution taskSolution) {
        Optional<BEvaluatorType> maybeType = BEvaluatorType.fromProgrammingLanguageName(taskSolution.getProgrammingLanguage());
        if (maybeType.isEmpty()) {
            return new BBackCheckedSolution(false, EvaluatingException.UNKNOWN_LANGUAGE.name(), Collections.emptyList());
        }
        BEvaluatorType type = maybeType.get();
        return check(taskSolution, type);
    }

    private static BBackCheckedSolution check(@Nonnull BBackTaskSolution taskSolution, @Nonnull BEvaluatorType type) {
        return type.getTaskEvaluator().check(taskSolution);
    }
}
