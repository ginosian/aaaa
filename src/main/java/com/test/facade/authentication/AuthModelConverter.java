package com.test.facade.authentication;

import com.test.entity.ApiUser;
import com.test.misc.TokenType;
import com.test.service.api_auth_access_token.model.ApiAuthAccessTokenCreationRequest;

import java.util.Date;

public class AuthModelConverter {
    public static ApiAuthAccessTokenCreationRequest convert(final ApiUser apiUser, final boolean isRememberMe) {
        final ApiAuthAccessTokenCreationRequest apiAuthAccessTokenCreationRequest = new ApiAuthAccessTokenCreationRequest();
        apiAuthAccessTokenCreationRequest.setApiUser(apiUser);
        apiAuthAccessTokenCreationRequest.setTokenType(isRememberMe ? TokenType.LOGIN_REMEMBER_ME : TokenType.LOGIN);
        apiAuthAccessTokenCreationRequest.setActive(true);
        apiAuthAccessTokenCreationRequest.setExpires(new Date());
        return apiAuthAccessTokenCreationRequest;
    }
}
