package by.salov.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


public class UserDetailsImpl implements UserDetails {
    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("read"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    /*срок действия учетной записи не истек*/
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /*срок действия аккауна не заблокирован*/
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /*Срок действия учетных данных не истек*/
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /*Включен*/
    @Override
    public boolean isEnabled() {
        return true;
    }
}
