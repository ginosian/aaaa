package com.test.facade.authentication.model;

import com.test.entity.ApiUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse{
    private ApiUser apiUser;
    private String token;
}
