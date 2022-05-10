package ua.nechay.bback.domain.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.nechay.bback.domain.TaskCompletionModel;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author anechaev
 * @since 17.04.2022
 */
@Entity
@Table(name = "users")
public class UserModel implements UserDetails {
    private static final int EXP_STEP = 50;
    private static final int MAX_LEVEL = 100;

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

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserRoleModel> roles;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "level")
    private Integer level;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<TaskCompletionModel> taskCompletions;

    public UserModel() {
    }

    public UserModel(long id, String login, String password, String eMail, Boolean isActive,
        Set<UserRoleModel> roles, Integer experience, Integer level, Set<TaskCompletionModel> taskCompletions)
    {
        this.id = id;
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
            .map(UserRoleModel::getRole)
            .map(BBackUserRole::getGrantedAuthorities)
            .flatMap(Set::stream)
            .collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String geteMail() {
        return eMail;
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

    public UserModel increaseExperience(Integer experience) {
        this.experience += experience;
        int threshold = determineThresholdForTheNextLevel(this.level);
        if (this.experience > threshold && this.level != MAX_LEVEL) {
            this.level = determineLevelBasedOnExp(this.experience);
        }
        return this;
    }

    private static int determineLevelBasedOnExp(int currentExperience) {
        int sum = 0;
        int previousLevel = 1;
        for (int level = 1; level <= MAX_LEVEL; level++) {
            sum += level * 50;
            if (sum > currentExperience) {
                return previousLevel;
            }
            previousLevel = level;
        }
        return previousLevel;
    }

    public int getPercentValueForNextLevel() {
        int thresholdForCurrentLevel = determineThresholdForTheNextLevel(this.level - 1);
        int thresholdForNextLevel = determineThresholdForTheNextLevel(this.level);
        int sum = thresholdForNextLevel - thresholdForCurrentLevel;
        int nowExp = this.experience - thresholdForCurrentLevel;
        return (nowExp * 100) / sum;
    }

    private static int determineThresholdForTheNextLevel(int currentLevel) {
        return IntStream.range(1, currentLevel + 1)
            .map(i -> i * EXP_STEP)
            .sum();
    }

    public Set<UserRoleModel> getRoleModels() {
        return roles;
    }

    public List<BBackUserRole> getRoles() {
        return roles.stream()
            .map(UserRoleModel::getRole)
            .collect(Collectors.toList());
    }

    public UserModel setRoles(Set<UserRoleModel> roles) {
        this.roles = roles;
        return this;
    }

    public static class Builder {
        private long id;
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
            UserModel user = new UserModel(id, login, password, eMail, isActive, Collections.emptySet(), experience, level, tasks);
            Set<UserRoleModel> userRoles = this.roles.stream()
                .map(role -> new UserRoleModel(role, user))
                .collect(Collectors.toSet());
            return user
                .setRoles(userRoles);
        }
    }
}
