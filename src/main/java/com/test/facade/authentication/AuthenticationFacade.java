package com.test.facade.authentication;

import com.test.facade.authentication.exception.AuthException;
import com.test.facade.authentication.model.AuthenticationRequest;
import com.test.facade.authentication.model.AuthenticationResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

public interface AuthenticationFacade  extends AuthenticationManager {

    AuthenticationResponse authenticateByCredentials(AuthenticationRequest request) throws AuthException;

    AuthenticationResponse authenticateByApiAccessToken(String token) throws AuthException;

    Authentication authenticate(Authentication authentication) throws AuthException;

    void logout(String token);
}
