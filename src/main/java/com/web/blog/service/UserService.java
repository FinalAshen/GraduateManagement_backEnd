package com.web.blog.service;

import org.json.JSONObject;

import javax.servlet.http.HttpSession;

/**
 * @author ashen
 * @date 2019/10/26 15.58
 */

public interface UserService {

    final static String IDENTITY = "Author";

    JSONObject login(HttpSession session, String account, String password);

    JSONObject logout(HttpSession session);

    JSONObject getInfo(HttpSession session);
}
