package cn.server.auth.serivce.impl;

import cn.server.auth.serivce.IUserService;
import cn.server.auth.entity.User;
import cn.server.auth.mapper.UserMapper;
import cn.server.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    public User findUserByUserName(String username){
        return userMapper.findUserByUserName(username);
    }


    //添加用户
    @Override
    public void addUser(User user) {
        //设置创建时间
        user.setCreateTime(new Date());
        //加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodePwd = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePwd);
        userMapper.addUser(user);
    }

    @Override
    public void updateUserHeadImg(User user) {
        userMapper.updateUserHeadImg(user);
    }

    /**
     * 修改用户
     */
    @Override
    public void editSaveUser(User user) {
        if(user.getPassword() != null && !"".equals(user.getPassword())) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        userMapper.editSaveUser(user);
    }
    /**
     * 删除用户
     */
    @Override
    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
    }
    /**
     * 批量删除
     */
    @Override
    public void batchRemove(List list) {
        userMapper.batchRemove(list);
    }
    /**
     * 学生选课
     */
    @Override
    public void editSaveXk(User user) {
        userMapper.editSaveXk(user);
    }

    @Override
    public void updatePwd(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userMapper.updatePwd(user);
    }
}
