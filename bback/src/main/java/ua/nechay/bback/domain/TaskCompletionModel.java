package ua.nechay.bback.domain;

import ua.nechay.bback.domain.user.UserModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.ZonedDateTime;

/**
 * @author anechaev
 * @since 24.04.2022
 */
@Entity
@Table(name = "task_completion")
public class TaskCompletionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    private TaskModel task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel user;

    @Enumerated(value= EnumType.STRING)
    @Column
    private BBackLanguage language;

    @Column(name = "time_of_completion")
    private ZonedDateTime timeOfCompletion;

    public TaskCompletionModel() {
    }

    public TaskCompletionModel(TaskModel task, UserModel user, BBackLanguage language, ZonedDateTime timeOfCompletion) {
        this.task = task;
        this.user = user;
        this.language = language;
        this.timeOfCompletion = timeOfCompletion;
    }

    public TaskModel getTask() {
        return task;
    }

    public static class Builder {
        private TaskModel task;
        private UserModel user;
        private BBackLanguage language;
        private ZonedDateTime timeOfCompletion;

        public Builder setTask(TaskModel task) {
            this.task = task;
            return this;
        }

        public Builder setUser(UserModel user) {
            this.user = user;
            return this;
        }

        public Builder setLanguage(BBackLanguage language) {
            this.language = language;
            return this;
        }

        public Builder setTimeOfCompletion(ZonedDateTime timeOfCompletion) {
            this.timeOfCompletion = timeOfCompletion;
            return this;
        }

        public TaskCompletionModel build() {
            return new TaskCompletionModel(task, user, language, timeOfCompletion);
        }
    }
}
