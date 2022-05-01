package com.example.lesson_4.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    public void authConfig(AuthenticationManagerBuilder auth,
                           UserDetailsService userDetailsService,
                           PasswordEncoder encoder) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("mem_admin")
                .password(encoder.encode("password"))
                .roles("ADMIN")
                .and()
                .withUser("mem_guest")
                .password(encoder.encode("password"))
                .roles("GUEST");

        auth.userDetailsService(userDetailsService);
    }
}
