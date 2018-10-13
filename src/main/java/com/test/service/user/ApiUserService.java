package com.test.service.user;

import com.test.entity.ApiUser;
import com.test.service.user.model.ApiUserCreateRequest;
import com.test.service.user.model.ApiUserUpdateRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface ApiUserService extends UserDetailsService {

    ApiUser loadUserByUsername(String s) throws UsernameNotFoundException;

    ApiUser get(String id);

    Optional<ApiUser> find(final String id);

    ApiUser create(ApiUserCreateRequest userRequest);

    ApiUser update(String id, ApiUserUpdateRequest userRequest);

    void delete(String id);

}
