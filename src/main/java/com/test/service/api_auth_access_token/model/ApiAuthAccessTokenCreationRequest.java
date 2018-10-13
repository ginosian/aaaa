package com.test.service.api_auth_access_token.model;

import com.test.entity.ApiUser;
import com.test.misc.TokenType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiAuthAccessTokenCreationRequest {
    private ApiUser apiUser;
    private TokenType tokenType;
    private boolean isActive;
    private Date expires;
}
