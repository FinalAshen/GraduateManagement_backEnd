package com.web.blog.service;

import org.json.JSONObject;

import javax.servlet.http.HttpSession;

public interface AdminService {
    final static String IDENTITY = "Admin";

    JSONObject login(HttpSession session, String id, String password);

    JSONObject logout(HttpSession session);
}
