package ua.nechay.bback.domain.user;

import org.springframework.security.core.GrantedAuthority;

import javax.annotation.Nonnull;

/**
 * @author anechaev
 * @since 18.04.2022
 */
public enum BBackUserPermission implements GrantedAuthority {
    ;

    private final String permission;

    BBackUserPermission(@Nonnull String permission) {
        this.permission = permission;
    }

    @Override
    public String getAuthority() {
        return permission;
    }
}
