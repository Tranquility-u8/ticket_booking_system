package com.example.service;

import com.example.entity.UserAuthEntity;
import com.example.entity.UserEntity;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface UserService {
    UserAuthEntity checkUser(String username, String password);

    UserEntity saveUser(UserEntity user);
    UserEntity findUserById(int id);
    List<UserEntity> findAllUsers();

    void saveUserAuth(UserAuthEntity auth);

    Object checkUserWithReturn(String username, String password);

    boolean isValueExist(String fieldName, Object value);
}
