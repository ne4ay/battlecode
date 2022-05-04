package ua.nechay.bback.domain;

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
 * @since 05.05.2022
 */
@Entity
@Table(name = "test_case")
public class TestCaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private TaskModel task;

    @Column
    private String inputCase;

    @Column
    private String expectedOutput;

    public TestCaseModel() {
    }

    public TestCaseModel(TaskModel task, String inputCase, String expectedOutput) {
        this.task = task;
        this.inputCase = inputCase;
        this.expectedOutput = expectedOutput;
    }

    public TaskModel getTask() {
        return task;
    }

    public String getInputCase() {
        return inputCase;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }
}
