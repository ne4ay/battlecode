package ua.nechay.bback.domain;

import ua.nechay.bback.api.TestCase;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author anechaev
 * @since 17.04.2022
 */
@Entity
@Table(name = "tasks")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private Integer version;

    @Column
    private String title;

    @Column(columnDefinition="TEXT")
    private String description;

    @Column
    private Integer cost;

    @Enumerated(value= EnumType.STRING)
    @Column
    private BBackTaskStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "complexity_id")
    private ComplexityModel complexity;

    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
    private Set<AttitudeChangeModel> attitudes;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    private Set<TaskCompletionModel> taskCompletions;

    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
    private Set<LanguageToTaskModel> languages;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    private Set<TestCaseModel> testCases;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "category_to_task",
        joinColumns = { @JoinColumn(name = "task_id") },
        inverseJoinColumns = { @JoinColumn(name = "category_id") }
    )
    private Set<CategoryModel> categories;



    public TaskModel() {
    }

    public TaskModel(long id, Integer version, String title, String description, BBackTaskStatus status, Integer cost, ComplexityModel complexity,
        Set<AttitudeChangeModel> attitudes, Set<LanguageToTaskModel> languages, Set<CategoryModel> categories,
        Set<TaskCompletionModel> taskCompletions, Set<TestCaseModel> testCases)
    {
        this.id = id;
        this.version = version;
        this.title = title;
        this.description = description;
        this.status = status;
        this.cost = cost;
        this.complexity = complexity;
        this.attitudes = attitudes;
        this.languages = languages;
        this.categories = categories;
        this.taskCompletions = taskCompletions;
        this.testCases = testCases;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCost() {
        return cost;
    }

    public ComplexityModel getComplexity() {
        return complexity;
    }

    public Set<AttitudeChangeModel> getAttitudes() {
        return attitudes;
    }

    public Set<LanguageToTaskModel> getLanguages() {
        return languages;
    }

    public BBackTaskStatus getStatus() {
        return status;
    }

    public Set<CategoryModel> getCategories() {
        return categories;
    }

    public Set<TestCaseModel> getTestCases() {
        return testCases;
    }

    public TaskModel setLanguages(Set<LanguageToTaskModel> languages) {
        this.languages = languages;
        return this;
    }

    public TaskModel setTestCases(Set<TestCaseModel> testCases) {
        this.testCases = testCases;
        return this;
    }

    public static class Builder {
        private long id;
        private String title;
        private String description;
        private Integer cost;
        private BBackTaskStatus status;
        private List<BBackLanguage> languages;
        private Collection<? extends TestCase> testCases;

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setCost(Integer cost) {
            this.cost = cost;
            return this;
        }

        public Builder setStatus(BBackTaskStatus status) {
            this.status = status;
            return this;
        }

        public Builder setLanguages(List<BBackLanguage> languages) {
            this.languages = languages;
            return this;
        }

        public Builder setTestCases(Collection<? extends TestCase> testCases) {
            this.testCases = testCases;
            return this;
        }

        public TaskModel build() {
            TaskModel task = new TaskModel(id, 0, title, description, status, cost, null,
                Collections.emptySet(), Collections.emptySet(), Collections.emptySet(), Collections.emptySet(), Collections.emptySet());
            Set<LanguageToTaskModel> langs = this.languages.stream()
                .map(lang -> new LanguageToTaskModel(lang, task))
                .collect(Collectors.toSet());
            Set<TestCaseModel> testCaseModels = this.testCases.stream()
                .map(testCase -> new TestCaseModel(task, testCase.getInputCase(), testCase.getExpectedOutput()))
                .collect(Collectors.toSet());
            return task
                .setLanguages(langs)
                .setTestCases(testCaseModels);
        }
    }
}
