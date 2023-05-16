package com.example.dao;

import com.example.entity.UserAuthEntity;
import com.example.entity.UserEntity;

import java.util.List;

public interface UserDao {

    UserAuthEntity checkUser(String username, String password);

    UserEntity save(UserEntity user);
    UserEntity findById(int id);
    List<UserEntity> findAll();

    UserAuthEntity save(UserAuthEntity auth);

    boolean isValueExist(String fieldName, Object value);
}
