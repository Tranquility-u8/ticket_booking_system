package com.example.daoimpl;

import com.example.dao.UserDao;
import com.example.entity.UserAuthEntity;
import com.example.entity.UserEntity;
import com.example.repository.UserAuthRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.constant.Constant.*;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserAuthEntity checkUser(String username, String password){
        return userAuthRepository.findByUsernameAndPassword(username,password);
    }
    public UserAuthEntity save(UserAuthEntity auth) {
        return userAuthRepository.save(auth);
    }


    @Override
    public boolean isValueExist(String fieldName, Object value){
        if(value == EMAIL){
            return userRepository.existsByEmail(fieldName);
        }

        if(value == TEL){
            return userRepository.existsByTel(fieldName);
        }

        if(value == USERNAME){
            return userAuthRepository.existsByUsername(fieldName);
        }

        return false;
    }
}
