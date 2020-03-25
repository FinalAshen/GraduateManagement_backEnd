package com.web.blog.controller;

import com.web.blog.entity.UserInfo;
import com.web.blog.service.UserService;
import com.web.blog.service.VisitorService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/visitor", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VisitorController {

    private final VisitorService visitorService;

    @Autowired
    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @RequestMapping(value = "/register")
    @ResponseBody
    public String register(HttpSession session, UserInfo userInfo) {
//        System.out.println(visitorService.register(session, userInfo).toString()+"66");
        System.out.println("66");
        return visitorService.register(session, userInfo).toString();
    }
}
