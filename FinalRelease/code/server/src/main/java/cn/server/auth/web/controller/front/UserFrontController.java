package cn.server.auth.web.controller.front;

import cn.server.auth.entity.User;
import cn.server.auth.serivce.IUserService;
import cn.server.common.result.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/frontuser")
public class UserFrontController {

    @Autowired
    private IUserService userService;


    @RequestMapping("/login")
    @ResponseBody
    public ResultResponse frontLogin(User user){
        User userdb = userService.findUserByUserName(user.getUsername());
        if(userdb == null){
            return ResultResponse.fail("用户不存在");
        }
        //如果存在比较密码
        BCryptPasswordEncoder bcEncoder = new BCryptPasswordEncoder();

        if(!bcEncoder.matches(user.getPassword(),userdb.getPassword())){
            return ResultResponse.fail("密码错误");
        }

        return ResultResponse.ok().put("user",userdb);
    }

    @RequestMapping("/reg")
    @ResponseBody
    public ResultResponse reg(User user){
        User userdb = userService.findUserByUserName(user.getUsername());
        if(userdb != null){
            return ResultResponse.fail("用户已存在!");
        }
        try {
            userService.addUser(user);
            return ResultResponse.ok();
        }catch (Exception e){
            return ResultResponse.fail("注册失败!");
        }


    }






}
