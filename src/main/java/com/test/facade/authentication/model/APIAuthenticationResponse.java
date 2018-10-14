package com.test.facade.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIAuthenticationResponse implements Authentication {

    private AuthenticationResponse authenticationResponse;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return authenticationResponse.getApiUser().getUsername();
    }

    @Override
    public Object getDetails() {
        return authenticationResponse;
    }

    @Override
    public Object getPrincipal() {
        return authenticationResponse.getApiUser();
    }

    @Override
    public boolean isAuthenticated() {
        return !StringUtils.isEmpty(authenticationResponse.getToken());
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
    }

    @Override
    public String getName() {
        return authenticationResponse.getApiUser().getUsername();
    }
}
