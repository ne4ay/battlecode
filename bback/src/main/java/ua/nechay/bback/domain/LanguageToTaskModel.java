package ua.nechay.bback.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Set;

/**
 * @author anechaev
 * @since 17.04.2022
 */
@Entity
@Table(name = "language_to_task")
public class LanguageToTaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(value= EnumType.STRING)
    @Column
    private BBackLanguage language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private TaskModel task;

    public LanguageToTaskModel(BBackLanguage language, TaskModel task) {
        this.language = language;
        this.task = task;
    }

    public LanguageToTaskModel() {

    }

    public BBackLanguage getLanguage() {
        return language;
    }
}
