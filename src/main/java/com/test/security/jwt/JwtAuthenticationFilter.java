package com.test.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.entity.ApiUser;
import com.test.facade.authentication.AuthenticationFacade;
import com.test.facade.authentication.exception.AuthException;
import com.test.facade.authentication.model.AuthenticationRequest;
import com.test.facade.authentication.model.AuthenticationResponse;
import com.test.mapper.ModelMapper;
import com.test.security.dto.AuthRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter  extends UsernamePasswordAuthenticationFilter {

    private AuthenticationFacade authenticationFacade;
    private ModelMapper modelMapper;

    @Autowired
    public JwtAuthenticationFilter(final AuthenticationFacade authenticationFacade, final ModelMapper modelMapper) {
        this.authenticationFacade = authenticationFacade;
        this.modelMapper = modelMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            final AuthRequestDto credentials = new ObjectMapper().readValue(req.getInputStream(), AuthRequestDto.class);
            final AuthenticationRequest authenticationRequest = modelMapper.map(credentials, AuthenticationRequest.class);
            final AuthenticationResponse authenticationResponse = authenticationFacade.authenticateByCredentials(authenticationRequest);
            final ApiUser apiUser = authenticationResponse.getApiUser();
            return new UsernamePasswordAuthenticationToken(apiUser, authenticationResponse.getToken(), apiUser.getAuthorities());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (AuthException e) {
            res.setStatus(e.getHttpStatusCode());
            return null;
        }
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        String token = (String)auth.getCredentials();
        res.addHeader("Authorization", "Bearer " + token);
    }

    @Override
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

}
