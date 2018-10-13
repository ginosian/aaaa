package com.test.service.token;

import com.test.service.api_auth_access_token.model.ApiAuthAccessTokenCreationRequest;

public interface TokenService {

    String create(ApiAuthAccessTokenCreationRequest request);

    String refresh(final String token);

    String getUserDetailId(final String token);
}
