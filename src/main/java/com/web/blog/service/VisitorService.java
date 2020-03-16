package com.web.blog.service;

import com.web.blog.entity.UserInfo;
import org.json.JSONObject;

import javax.servlet.http.HttpSession;

public interface VisitorService {
    final static String Identity = "Visitor";
    JSONObject register(HttpSession session, UserInfo user);
}
