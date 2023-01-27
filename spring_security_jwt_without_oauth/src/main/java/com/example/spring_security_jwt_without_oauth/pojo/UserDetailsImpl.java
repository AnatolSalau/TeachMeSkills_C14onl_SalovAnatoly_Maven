package com.example.spring_security_jwt_without_oauth.pojo;

import com.example.spring_security_jwt_without_oauth.entities.Role;
import com.example.spring_security_jwt_without_oauth.entities.User;
import com.example.spring_security_jwt_without_oauth.enums.RolesAll;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode

public class UserDetailsImpl implements UserDetails {
    private final String name;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public UserDetailsImpl (User user) {
        this.name = user.getLogin();
        this.password = user.getPassword();
        authorities = user.getRoles().stream()
                .map(role -> new
                        SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList()) ;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
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
        return true;
    }
}
