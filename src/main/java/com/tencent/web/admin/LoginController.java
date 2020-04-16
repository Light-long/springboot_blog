package com.tencent.web.admin;

import com.tencent.entity.User;
import com.tencent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String loginPage() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {

        User user = userService.checkUer(username, password);
        if (user != null) {
            session.setAttribute("user",user);
            return "/admin/index";
        } else {
//            attributes = new RedirectAttributesModelMap();
            attributes.addFlashAttribute("msg","用户名或密码错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("/loginOut")
    public String loginOut(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
