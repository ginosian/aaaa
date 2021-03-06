package com.test.facade.authentication.impl;


import com.test.entity.ApiAuthAccessToken;
import com.test.entity.ApiUser;
import com.test.facade.authentication.AuthModelConverter;
import com.test.facade.authentication.AuthenticationFacade;
import com.test.facade.authentication.exception.AuthException;
import com.test.facade.authentication.model.APIAuthenticationResponse;
import com.test.facade.authentication.model.AuthenticationRequest;
import com.test.facade.authentication.model.AuthenticationResponse;
import com.test.facade.strategy.AuthValidationStrategy;
import com.test.service.api_auth_access_token.ApiAuthAccessTokenService;
import com.test.service.api_auth_access_token.model.ApiAuthAccessTokenRequest;
import com.test.service.user.ApiUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import static org.springframework.util.Assert.hasText;

@Service
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationFacadeImpl.class);

    @Autowired
    @Qualifier("api-user-service")
    private ApiUserService apiUserDetailService;

    @Autowired
    private ApiAuthAccessTokenService apiAuthAccessTokenService;

    @Autowired
    private AuthValidationStrategy authValidationStrategy;

    @Value("#{'${authenticationService.masterApiUserDetail.passwordHash}' ?: null}")
    private String masterApiUserDetailPasswordHash;

    @Override
    public AuthenticationResponse authenticateByCredentials(final AuthenticationRequest request) throws AuthException {
        authValidationStrategy.validate(request);
        final ApiUser apiUser = apiUserDetailService.loadUserByUsername(request.getUsername());
        authValidationStrategy.validate(apiUser);
        final ApiAuthAccessToken apiAuthAccessToken = apiAuthAccessTokenService.createApiAccessToken(AuthModelConverter.convert(apiUser, request.isRememberMe()));
        return new AuthenticationResponse(apiUser, apiAuthAccessToken.getToken());
    }

    @Override
    public AuthenticationResponse authenticateByApiAccessToken(final String token) throws AuthException {
        ApiAuthAccessToken existingToken = apiAuthAccessTokenService.findByToken(token).orElse(null);
        authValidationStrategy.validateForRefreshing(existingToken);
        if (authValidationStrategy.isExpiredLoginToken(existingToken)) {
            apiAuthAccessTokenService.inactivateApiAccessToken(new ApiAuthAccessTokenRequest(existingToken));
            throw new AuthException(String.format("Api user access token is expired:'%s'.", existingToken.getApiUser().getId()));
        }
        if (authValidationStrategy.isExpiringRefreshToken(existingToken)) {
            existingToken = apiAuthAccessTokenService.refreshApiAccessToken(new ApiAuthAccessTokenRequest(existingToken));
        }
        return new AuthenticationResponse(existingToken.getApiUser(), existingToken.getToken());
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthException {
        return new APIAuthenticationResponse(authenticateByCredentials((AuthenticationRequest) authentication.getDetails()));
    }

    @Override
    public void logout(String token) {
        hasText(token, "token can not be null or empty.");
        ApiAuthAccessToken existingToken = apiAuthAccessTokenService.findByToken(token).orElse(null);
        if (existingToken != null) {
            apiAuthAccessTokenService.deleteApiAccessToken(new ApiAuthAccessTokenRequest(existingToken));
        }
    }
}
