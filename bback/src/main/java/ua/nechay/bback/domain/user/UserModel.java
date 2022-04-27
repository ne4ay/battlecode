package ua.nechay.bback.domain.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.nechay.bback.domain.TaskCompletionModel;
import ua.nechay.bback.domain.TaskModel;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author anechaev
 * @since 17.04.2022
 */
@Entity
@Table(name = "users")
public class UserModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String eMail;

    @Column
    private Boolean isActive;

    @ElementCollection(targetClass = BBackUserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<BBackUserRole> roles;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "level")
    private Integer level;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    private Set<TaskCompletionModel> taskCompletions;

    public UserModel() {
    }

    public UserModel(String login, String password, String eMail, Boolean isActive,
        Set<BBackUserRole> roles, Integer experience, Integer level, Set<TaskCompletionModel> taskCompletions)
    {
        this.login = login;
        this.password = password;
        this.eMail = eMail;
        this.isActive = isActive;
        this.roles = roles;
        this.experience = experience;
        this.level = level;
        this.taskCompletions = taskCompletions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
            .map(BBackUserRole::getGrantedAuthorities)
            .flatMap(Set::stream)
            .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    public String getEmail() {
        return eMail;
    }

    public Integer getExperience() {
        return experience;
    }

    public Integer getLevel() {
        return level;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public UserModel setActive(Boolean active) {
        this.isActive = active;
        return this;
    }

    public static class Builder {
        private String login;
        private String password;
        private String eMail;
        private Boolean isActive;
        private Set<BBackUserRole> roles;
        private Integer experience;
        private Integer level;
        private Set<TaskCompletionModel> tasks;

        public Builder() {

        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setEMail(String eMail) {
            this.eMail = eMail;
            return this;
        }

        public Builder setActive(Boolean active) {
            isActive = active;
            return this;
        }

        public Builder setRoles(Set<BBackUserRole> roles) {
            this.roles = roles;
            return this;
        }

        public Builder setExperience(Integer experience) {
            this.experience = experience;
            return this;
        }

        public Builder setLevel(Integer level) {
            this.level = level;
            return this;
        }

        public Builder setTasks(Set<TaskCompletionModel> tasks) {
            this.tasks = tasks;
            return this;
        }

        public UserModel build() {
            return new UserModel(login, password, eMail, isActive, roles, experience, level, tasks);
        }
    }
}
