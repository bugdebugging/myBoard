package com.anny.board.boardme.security.jwt;

import com.anny.board.boardme.dto.user.AuthRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtCreatorFilter extends UsernamePasswordAuthenticationFilter {
    private final ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public JwtCreatorFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.objectMapper = new ObjectMapper();
        this.jwtUtils = jwtUtils;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            AuthRequest authRequest = objectMapper.readValue(request.getInputStream(), AuthRequest.class);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword());
            return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return super.attemptAuthentication(request, response);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        response.addHeader("Authorization", jwtUtils.createToken(authResult.getName()));
    }
}
