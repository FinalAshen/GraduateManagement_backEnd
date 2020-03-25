package com.web.blog.controller;

import com.web.blog.dao.UserDao;
import com.web.blog.entity.UserInfo;
import com.web.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author ashen
 * @date 2019/10/26 15.58
 */
@Controller
@RequestMapping(value = "/user", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(HttpSession session, String account, String password) {
        System.out.println("s");
        return userService.login(session, account, password).toString();
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public String logout(HttpSession session) {
        return userService.logout(session).toString();
    }


    @RequestMapping(value = "/getInfo")
    @ResponseBody
    public String getInfo(HttpSession session) {
        return userService.getInfo(session).toString();
    }
}
