package it.xdu.xzk.citybusmanagersystem.controller;

import it.xdu.xzk.citybusmanagersystem.entity.User;
import it.xdu.xzk.citybusmanagersystem.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        // 显示登录页面
        return "login";
    }

    @PostMapping("/login")
    public String loginController(@RequestParam("username") String uname, @RequestParam("password") String password, Model model) {
        User user = userService.loginService(uname, password);
        if (user != null) {
            // 登录成功，重定向到home页面
            System.out.println("登录成功:" + user.getUsername());
            return "home";
        } else {
            // 登录失败，添加错误信息到模型，并返回登录页面
            model.addAttribute("error", "账号或密码错误！");
            System.out.println("登录失败，用户名或密码不正确。");
            return "login";
        }
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        // 显示注册表单
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerController(@RequestParam String username,@RequestParam String password ,Model model) {
        // 创建User对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        // 保存到数据库
        User user1 = userService.registerService(user);
        System.out.println("Saved User: " + user.getUsername());
        model.addAttribute("successMessage","注册成功！");
        return "login";
    }
}
