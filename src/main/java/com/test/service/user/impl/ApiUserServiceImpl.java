package com.test.service.user.impl;

import com.test.entity.ApiUser;
import com.test.repository.ApiUserRepository;
import com.test.service.user.ApiUserService;
import com.test.service.user.model.ApiUserCreateRequest;
import com.test.service.user.model.ApiUserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "api-user-service")
public class ApiUserServiceImpl implements ApiUserService {

    @Autowired
    private ApiUserRepository apiUserRepository;

    @Override
    public ApiUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return apiUserRepository.findByUsernameAndDeletedFalse(username);
    }

    @Override
    public ApiUser get(String id) {
        return null;
    }

    @Override
    public Optional<ApiUser> find(String id) {
        return Optional.empty();
    }

    @Override
    public ApiUser create(ApiUserCreateRequest userRequest) {
        return null;
    }

    @Override
    public ApiUser update(String id, ApiUserUpdateRequest userRequest) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
