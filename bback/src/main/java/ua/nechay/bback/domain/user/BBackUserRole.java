package ua.nechay.bback.domain.user;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author anechaev
 * @since 18.04.2022
 */
public enum BBackUserRole {
    GLOBAL_ADMINISTRATOR(Sets.newHashSet()),
    DEFAULT_USER(Sets.newHashSet());

    private final Set<BBackUserPermission> permissions;

    BBackUserRole(Set<BBackUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<BBackUserPermission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthorities() {
        return getPermissions().stream()
            .map(permission -> (GrantedAuthority) permission)
            .collect(Collectors.toSet());
    }

}
