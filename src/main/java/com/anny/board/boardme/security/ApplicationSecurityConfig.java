package com.anny.board.boardme.security;

import com.anny.board.boardme.security.jwt.JwtAuthenticationFilter;
import com.anny.board.boardme.security.jwt.JwtCreatorFilter;
import com.anny.board.boardme.security.jwt.JwtUtils;
import com.anny.board.boardme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtCreatorFilter jwtCreatorFilter = new JwtCreatorFilter(authenticationManager(), jwtUtils);
        jwtCreatorFilter.setFilterProcessesUrl("/auth/token");

        http.csrf().disable()
                .addFilter(jwtCreatorFilter)
                .addFilterAfter(new JwtAuthenticationFilter(jwtUtils), JwtCreatorFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/auth/token", "/auth/join").permitAll()
                .antMatchers("/api/common").permitAll()
                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}
