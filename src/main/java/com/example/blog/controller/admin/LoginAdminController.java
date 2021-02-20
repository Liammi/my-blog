package com.example.blog.controller.admin;

import com.example.blog.pojo.User;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cKSqHjRCAnTwsRUca4tL")
public class LoginAdminController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String loginPage(HttpSession httpSession) {
        if (httpSession.getAttribute("user") != null) {
            return "admin/index";
        } else {
            return "admin/login";
        }
    }

    /*处理管理员登陆请求*/
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession httpSession,
                        RedirectAttributes attributes) {
        User user = userService.CheckUser(username, password);
        if (user != null) {
            user.setPassword(null);
            httpSession.setAttribute("user", user);
            return "admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名密码错误");//采用重定向的方式返回admin页面
            return "redirect:/cKSqHjRCAnTwsRUca4tL";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/cKSqHjRCAnTwsRUca4tL";
    }
}
