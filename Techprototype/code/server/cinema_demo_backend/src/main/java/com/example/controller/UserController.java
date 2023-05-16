package com.example.controller;

import com.example.entity.UserAuthEntity;
import com.example.entity.UserEntity;
import com.example.request.RegisterRequest;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.constant.Constant.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")// 其实是比对数据库中是否有这样的username-password记录
    public Object login(@RequestParam("username")String username, @RequestParam("password")String password) {
        // 处理登录请求，比对信息，若成功登录，则包装好信息返回，包装过程见Service
        return userService.checkUserWithReturn(username, password);
    }

    // 处理注册请求，要求request中有这些属性（至少是必要的not null属性）
    @RequestMapping("/register")// 此处二选一进行解析，因为request可能是JSON或者form-data形式的，那么选一个有效的解析即可
    public ResponseEntity<?> register(@ModelAttribute RegisterRequest formData,
                                      @RequestBody(required = false) RegisterRequest jsonData) {
        RegisterRequest request = (jsonData != null) ? jsonData : formData;
        if (userService.isValueExist(request.getUsername(), USERNAME)) {// 必须判断是否重名
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }

        if (userService.isValueExist(request.getTel(), TEL)) {// 必须判断是否重复使用电话号码
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tel already exists");
        }

        if (userService.isValueExist(request.getEmail(), EMAIL)) {// 必须判断是否重复使用email
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists");
        }

        if (Objects.equals(request.getType(), ADMIN)) {// 若是注册管理员账号，必须检查token
            if (!isValidAdminToken(request.getAdminToken())) {// 待实现判断管理员TOKEN的功能
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid admin token");
            }
        }

        UserEntity user = new UserEntity();
        user.setName(request.getName());
        user.setNickname(request.getNickname());
        user.setEmail(request.getEmail());
        user.setTel(request.getTel());
        user.setAddress(request.getAddress());
        user.setAvatarUrl(request.getAvatarUrl());

        userService.saveUser(user);// 存了user之后，会将自增的user_id赋给这个user，这是Ok的

        UserAuthEntity auth = new UserAuthEntity();
        auth.setUsername(request.getUsername());
        auth.setPassword(request.getPassword());
        auth.setType(request.getType());

        user.setUserAuthEntity(auth);
        auth.setUser(user);// 使用save后的user来初始化userId！

        userService.saveUserAuth(auth);

        return ResponseEntity.ok().build();
    }

    // 通过用户名密码来获取信息，与login逻辑相同
    @RequestMapping("/checkUser")
    public Object checkUser(@RequestParam("username") String username,@RequestParam("password") String password){
        return userService.checkUserWithReturn(username, password);
    }

    private boolean isValidAdminToken(String adminToken) {
        // TODO: implement validation logic
        return true;
    }
}
