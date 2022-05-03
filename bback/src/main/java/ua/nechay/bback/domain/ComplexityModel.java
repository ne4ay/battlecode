package ua.nechay.bback.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author anechaev
 * @since 17.04.2022
 */
@Entity
@Table(name = "complexities")
public class ComplexityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    public ComplexityModel() {
    }

    public String getName() {
        return name;
    }
}
