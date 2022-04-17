package ua.nechay.bback.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import java.util.Set;

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

    @Column
    private String description;

    @Column
    private Integer cost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "complexity_id")
    private ComplexityModel complexity;

    @OneToMany(mappedBy = "task")
    private Set<AttitudeChangeModel> attitudeChanges;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "language_to_task",
        joinColumns = { @JoinColumn(name = "task_id") },
        inverseJoinColumns = { @JoinColumn(name = "lang_id") }
    )
    private Set<LanguageModel> languages;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "category_to_task",
        joinColumns = { @JoinColumn(name = "task_id") },
        inverseJoinColumns = { @JoinColumn(name = "category_id") }
    )
    private Set<CategoryModel> categories;



    public TaskModel() {
    }

    public TaskModel(Integer version, String title, String description, Integer cost,
        ComplexityModel complexity, Set<LanguageModel> languages, Set<CategoryModel> categories)
    {
        this.version = version;
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.complexity = complexity;
        this.languages = languages;
        this.categories = categories;
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
}
