package com.test.repository;

import com.test.entity.ApiUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiUserRepository extends JpaRepository<ApiUser, String> {

    ApiUser findByUsernameAndDeletedFalse(String username);
}
