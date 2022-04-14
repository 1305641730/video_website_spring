package com.kurumi.controller;

import com.kurumi.domain.User;
import com.kurumi.service.UserService;
import com.kurumi.utils.FileUtils;
import com.kurumi.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.smtp.username}")
    private String emailSender;

    @GetMapping
    public ResObj selectAll() {
        List<User> userList = userService.selectAll();
        return new ResObj(true, userList);
    }

    @GetMapping("/{id}")
    public ResObj selectById(@PathVariable Integer id) {
        User user = userService.selectById(id);
        return new ResObj(true, user);
    }

    @PostMapping("/sendcode")
    public ResObj sendCode(@RequestParam String username, @RequestParam String email, HttpSession session, HttpServletRequest request) {
        // 生成6为验证码
        String Captcha = String.valueOf(new Random().nextInt(899999) + 100000);
        System.out.println(username + "," + Captcha + "," + emailSender);
        request.getSession().setAttribute(username, Captcha);
        System.out.println("session===" + request.getSession().getAttribute(username) + ",sessionID=" + request.getSession().getId());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            // 发送人邮件地址
            message.setFrom(emailSender);
            message.setTo(email);
            message.setSubject("验证码");
            message.setText("接收到的验证码为：" + Captcha);
            javaMailSender.send(message);
            return new ResObj(true, "验证码发送成功，请注意查看邮箱！", session.getId());
        } catch (Exception e) {
            return new ResObj(true, "验证码发送失败，请稍后再试！", e.getMessage());
        }
    }

    @GetMapping("/show")
    public void testSession(@RequestParam String username, HttpServletRequest request) {
        System.out.println("username=" + username);
        System.out.println(request.getSession().getAttribute(username) + "," + request.getSession().getId());
    }

    @PostMapping("/exist")
    public ResObj isExist(@RequestBody User user) {
        boolean exist = userService.isExist(user);
        return new ResObj(true, exist?"用户名已存在!":"用户名可用", exist);
    }

    @PostMapping("/register")
    public ResObj register(@RequestBody User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String validateCode = (String) session.getAttribute(user.getUsername());
        System.out.println(session.getAttribute(user.getUsername()) + "," + user.getUsername() + "," + session.getId());
        if(validateCode != null && validateCode.equals(user.getCode())) {
            boolean result = userService.register(user);
            return new ResObj(true, result?"用户注册成功！":"用户注册失败，请重新注册！", result);
        }
        return new ResObj(true, "输入的验证码不正确，请稍后再试", false);
    }

    @PostMapping("/login")
    public ResObj login(@RequestBody User user) {
        User loginUser = userService.login(user);
        String token = null;
        if(loginUser != null) {
            token = JwtUtils.createToken();
            loginUser.setToken(token);
            loginUser.setPassword(null);
        }
        return new ResObj(true, loginUser != null?"用户登录成功！":"用户登录失败，请重新登录！", loginUser);
    }

    @PostMapping("/validate")
    public ResObj validate(@RequestBody String token) {
//        System.out.println(token);
        boolean result = JwtUtils.validateJwt(token);
        return new ResObj(true, result?"token校验成功！":"token校验失败！", result);
    }

    @PutMapping
    public ResObj updateUser(@RequestBody User user) {
        boolean result = userService.updateUser(user);
        return new ResObj(true, result? "修改信息成功！": "修改信息失败，请重新再试！", result);
    }

    @PostMapping("/uploadAvatar")
    public ResObj uploadAvatar(MultipartFile file, HttpServletRequest request) {
        ResObj result = new ResObj();
        try {
            String url = FileUtils.uploadSimpleFile(file, request, "avatars");
            result.setFlag(true);
            result.setMsg("上传头像成功!");
            result.setData(url);
        } catch(IOException e) {
            result.setFlag(false);
            result.setMsg("error");
            result.setData(e.getMessage());
        }
        return result;
    }
}
