package it.xdu.xzk.citybusmanagersystem.controller;

import it.xdu.xzk.citybusmanagersystem.entity.User;
import it.xdu.xzk.citybusmanagersystem.service.UserService;
import it.xdu.xzk.citybusmanagersystem.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result loginController(@RequestParam("username") String uname, @RequestParam("password") String password){
        User user = userService.loginService(uname, password);
        if(user!=null){
            return Result.success(user,"登录成功！");
        }else{
            return Result.error("123","账号或密码错误！");
        }
    }

    @PostMapping("/register")
    public Result registController(@RequestBody User newUser){
        User user = userService.registerService(newUser);
        if(user!=null){
            return Result.success(user,"注册成功！");
        }else{
            return Result.error("456","用户名已存在！");
        }
    }
}
