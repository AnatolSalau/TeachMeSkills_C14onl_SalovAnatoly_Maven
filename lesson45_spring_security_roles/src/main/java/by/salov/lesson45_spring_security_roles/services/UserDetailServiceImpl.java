package by.salov.lesson45_spring_security_roles.services;

import by.salov.lesson45_spring_security_roles.dao.UserRepository;
import by.salov.lesson45_spring_security_roles.models.Role;
import by.salov.lesson45_spring_security_roles.models.User;
import by.salov.lesson45_spring_security_roles.models.impl.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByLogin(username).orElseThrow( () ->
                new UsernameNotFoundException(username));
        List<Role> roleList = user.getRoleList();
        List<String> roleNames = roleList.stream()
                .map(Role::getName)
                .collect(Collectors.toList());
        return new UserDetailsImpl(user.getLogin(),user.getPassword(),roleNames);
    }
}
