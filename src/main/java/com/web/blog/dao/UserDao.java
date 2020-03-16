package com.web.blog.dao;

import com.web.blog.entity.UserInfo;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Repository
public interface UserDao {
    // 一个邮箱只能注册一个账号
    UserInfo getInfoByAccount(String account);
    int getInfoByEmail(String email);
    int doRegister(UserInfo userInfo);
}
