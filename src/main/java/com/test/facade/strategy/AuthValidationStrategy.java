package com.test.facade.strategy;

import com.test.entity.ApiAuthAccessToken;
import com.test.entity.ApiUser;
import com.test.facade.authentication.PasswordHashHelper;
import com.test.facade.authentication.exception.AuthException;
import com.test.facade.authentication.model.AuthenticationRequest;
import com.test.misc.TokenType;
import com.test.service.api_auth_access_token.ApiAuthAccessTokenService;
import com.test.service.api_auth_access_token.model.ApiAuthAccessTokenRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

import static org.apache.commons.lang3.Validate.notNull;
import static org.springframework.util.Assert.hasText;

@Component
public class AuthValidationStrategy {

    private static final Logger logger = LoggerFactory.getLogger(AuthValidationStrategy.class);

    @Autowired
    private ApiAuthAccessTokenService apiAuthAccessTokenService;

    public void validate(final ApiUser apiUser){
        notNull(apiUser, "userDetail can not be null");
        final String userId = apiUser.getId();
        final String username = apiUser.getUsername();
        final String plainPassword = apiUser.getPassword();
        hasText(userId, "userDetail.userId can not be null or empty.");
        hasText(username, "userDetail.username can not be null or empty.");
        hasText(plainPassword, "userDetail.plainPassword can not be null or empty.");

        logger.debug("Attempting authentication with username for user:'{}'...", userId);
        if(!PasswordHashHelper.isPasswordCorrect(plainPassword)){
            logger.debug("Password validation failed for user:'{}'.", userId);
            throw new AuthException(String.format("Password validation failed for user:'%s'.", userId));
        }
        logger.trace("Password validation passed for user:'{}'.", userId);
    }

    public void validate(final AuthenticationRequest request){
        Assert.notNull(request, "authenticationRequest.request cannot be null.");
        Assert.notNull(request.getUsername(), "authenticationRequest.request.username cannot be null.");
        Assert.notNull(request.getPlainPassword(), "authenticationRequest.request.plainPassword cannot be null.");
    }

    public void validateForRefreshing(final ApiAuthAccessToken token){
        if(token == null || token.isDeleted() || (!isRememberMe(token) && (isExpired(token.getExpires()) || !token.getActive()))){
            apiAuthAccessTokenService.inactivateApiAccessToken(new ApiAuthAccessTokenRequest(token));
            throw new AuthException("ApiAccessToken does not exist or is invalid or is expired.");
        }
    }

    public boolean isExpiringRefreshToken(final ApiAuthAccessToken token){
        return isRememberMe(token) && (isExpired(token.getExpires()) || isExpiring(token.getExpires()));
    }

    public boolean isExpiredLoginToken(final ApiAuthAccessToken token){
        return isLogin(token) && (isExpired(token.getExpires()) || !token.getActive() || token.isDeleted());
    }

    private boolean isExpiring(final LocalDateTime expirationTime) {
        return expirationTime.plusMinutes(1).isAfter(LocalDateTime.now());
    }

    private boolean isExpired(final LocalDateTime expirationTime) {
        return expirationTime.isBefore(LocalDateTime.now());
    }

    private boolean isRememberMe(final ApiAuthAccessToken token) {
        return token.getTokenType().equals(TokenType.LOGIN_REMEMBER_ME);
    }

    private boolean isLogin(final ApiAuthAccessToken token) {
        return token.getTokenType().equals(TokenType.LOGIN);
    }


}
