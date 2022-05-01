package ua.nechay.bback.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nechay.bback.domain.BBackLanguage;
import ua.nechay.bback.domain.LanguageToTaskModel;

import java.util.List;

/**
 * @author anechaev
 * @since 01.05.2022
 */
@Repository
public interface LanguageToTaskRepo extends JpaRepository<LanguageToTaskModel, Long> {

    List<LanguageToTaskModel> findLanguageToTaskModelsByLanguage(BBackLanguage language);

    int countLanguageToTaskModelByLanguage(BBackLanguage language);
}
