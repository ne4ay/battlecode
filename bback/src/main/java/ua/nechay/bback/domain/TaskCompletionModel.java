package ua.nechay.bback.domain;

import ua.nechay.bback.domain.user.UserModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private TaskModel task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel user;

    @Column(name = "time_of_completion")
    private long timeOfCompletion;

    public TaskCompletionModel() {
    }

    public TaskCompletionModel(TaskModel task, UserModel user, long timeOfCompletion) {
        this.task = task;
        this.user = user;
        this.timeOfCompletion = timeOfCompletion;
    }
}
