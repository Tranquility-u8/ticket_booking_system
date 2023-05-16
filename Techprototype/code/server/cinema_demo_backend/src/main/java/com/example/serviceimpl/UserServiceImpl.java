package com.example.serviceimpl;

import com.example.dao.UserDao;
import com.example.entity.UserAuthEntity;
import com.example.entity.UserEntity;
import com.example.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserAuthEntity checkUser(String username, String password){
        return userDao.checkUser(username,password);
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        return userDao.save(user);
    }

    @Override
    public UserEntity findUserById(int id) {
        return userDao.findById(id);
    }

    @Override
    public List<UserEntity> findAllUsers() {
        return userDao.findAll();
    }

    @Override
    public void saveUserAuth(UserAuthEntity auth) {
        userDao.save(auth);
    }

    @Override
    public Object checkUserWithReturn(String username, String password) {
        // 这里原本会抛出throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
        // 可以在函数声明后接上这三句话来往上传，例如public Object checkUserWithReturn(String username, String password) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
        // 为了避免往上传，这里catch掉他们
        try{
            UserAuthEntity auth = checkUser(username, password);
            if(auth != null){
                Map<String, Object> result = new HashMap<>();// 这里从auth获取userId, username, type即可，必不能返回password
                result.put("userId", auth.getUserId());
                result.put("username", auth.getUsername());
                result.put("type", auth.getType());
                Map<String, String> userMap = BeanUtils.describe(auth.getUser());
                userMap.remove("class");
                result.putAll(userMap);
                result.remove("userAuthEntity");
                return result;
            }
        }
        catch (IllegalAccessException e) {
            // 处理 IllegalAccessException
        } catch (InvocationTargetException e) {
            // 处理 InvocationTargetException
//            Throwable cause = e.getCause();
            // cause 即为被包装的原始异常
        } catch (NoSuchMethodException e) {
            // 处理 NoSuchMethodException
        }

        return "checkUserFail";
    }

    @Override
    public boolean isValueExist(String fieldName, Object value){
        return userDao.isValueExist(fieldName, value);
    }


}
