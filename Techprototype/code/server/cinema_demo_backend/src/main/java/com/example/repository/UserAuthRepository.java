package com.example.repository;

import com.example.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuthEntity, Integer> {
    UserAuthEntity findByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);
}
