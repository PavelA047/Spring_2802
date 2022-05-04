package com.example.lesson_4.security;

import com.example.lesson_4.persist.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .map(user -> new User(
                        user.getUsername(),
                        user.getPassword(),
                        user.getRoleSet()
                                .stream()
                                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                                .collect(Collectors.toList())
                        //Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
                ))
                .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found"));
    }
}
