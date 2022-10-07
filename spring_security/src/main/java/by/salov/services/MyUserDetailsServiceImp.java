package by.salov.services;

import by.salov.dao.MyUserJpaRepository;
import by.salov.models.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private MyUserJpaRepository myUserRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<MyUser> myUserOptional = myUserRepository.findMyUserByLogin(login);
        MyUser myUser = myUserOptional.orElseThrow(() ->new UsernameNotFoundException("Login not found" + myUserOptional.get().getLogin()));
        UserDetails userDetails = User.builder()
                .username(myUser.getLogin())
                .password(myUser.getPassword())
                .roles(myUser.getRole())
                .build();
        return userDetails;
    }
}
