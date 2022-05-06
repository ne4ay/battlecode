package ua.nechay.bevaluator;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.nechay.bback.BBackCheckedSolution;
import ua.nechay.bback.BBackTaskSolution;

/**
 * @author anechaev
 * @since 06.05.2022
 */
@RestController
@RequestMapping("/check")
public class CheckController {


    @PostMapping
    public @ResponseBody BBackCheckedSolution checkSolution(@RequestBody BBackTaskSolution taskSolution) {
        return null;
    }
}
