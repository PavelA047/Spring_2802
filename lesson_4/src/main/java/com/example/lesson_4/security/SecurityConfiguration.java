package com.example.lesson_4.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.stream.Collectors;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
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

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/rest/**")
                    .authorizeRequests()
                    .anyRequest().permitAll()
                    .and()
                    .httpBasic()
                    .and()
                    .csrf().disable()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }
    }

    @Configuration
    @Order(2)
    public static class UiWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests()
                    .antMatchers("/**/*.css", "/**/*.js").permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers("/access_denied").authenticated()
                    .antMatchers("/user").hasAnyRole("ADMIN", "SUPER_ADMIN")
                    .antMatchers("/user/**").hasAnyRole("SUPER_ADMIN")
                    .antMatchers("/product").hasAnyRole("ADMIN", "SUPER_ADMIN", "GUEST")
                    .antMatchers("/product/**").hasAnyRole("ADMIN", "SUPER_ADMIN")
                    .and()
                    .formLogin()
                    //.loginPage("/login")
                    //.defaultSuccessUrl("*/")
                    .successHandler((request, response, authentication) -> {
                        Set<String> auths = authentication.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toSet());
                        if (auths.contains("ROLE_ADMIN") || auths.contains("ROLE_SUPER_ADMIN")) {
                            response.sendRedirect("/user");
                        } else {
                            response.sendRedirect("/");
                        }
                    })
                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/access_denied");
        }
    }
}
