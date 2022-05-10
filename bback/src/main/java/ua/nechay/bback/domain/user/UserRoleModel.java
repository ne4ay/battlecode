package ua.nechay.bback.domain.user;

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

/**
 * @author anechaev
 * @since 10.05.2022
 */
@Entity
@Table(name = "user_role")
public class UserRoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private BBackUserRole role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserModel user;

    public UserRoleModel(BBackUserRole role, UserModel user) {
        this.role = role;
        this.user = user;
    }

    public UserRoleModel() {
    }

    public BBackUserRole getRole() {
        return role;
    }

    public UserModel getUser() {
        return user;
    }
}
