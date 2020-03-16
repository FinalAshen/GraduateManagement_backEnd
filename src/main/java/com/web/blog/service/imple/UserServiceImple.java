package com.web.blog.service.imple;

import com.web.blog.dao.UserDao;
import com.web.blog.entity.UserInfo;
import com.web.blog.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Feedback;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Service
public class UserServiceImple implements UserService {

    private final UserDao userDao;

    @Autowired

    public UserServiceImple(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public JSONObject login(HttpSession session, String account, String password) {

        if (session.getAttribute(UserService.IDENTITY) != null) {
            session.removeAttribute(UserService.IDENTITY);
        }

        UserInfo user = userDao.getInfoByAccount(account);
        if (user != null && user.getPassword().equals(password)) {
            user.setPassword(null);
            session.setAttribute(UserService.IDENTITY, user);
            return Feedback.info(UserService.IDENTITY, Feedback.STATUS_SUCCESS);
        }

        return Feedback.info("账号或密码错误", Feedback.STATUS_ERROR);
    }

    @Override
    public JSONObject logout(HttpSession session) {
        if (session.getAttribute(UserService.IDENTITY) == null) {
            return Feedback.info("未登录", Feedback.STATUS_ERROR);
        }
        session.removeAttribute(UserService.IDENTITY);
        return Feedback.info("注销成功", Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject getInfo(HttpSession session) {
        if (session.getAttribute(UserService.IDENTITY) != null) {
            UserInfo user = (UserInfo)session.getAttribute(UserService.IDENTITY);
            HashMap<String, Object> hash = new HashMap<>();
            UserInfo userInfo = userDao.getInfoByAccount(user.getAccount());
            userInfo.setPassword("");
            hash.put(UserService.IDENTITY, userInfo);
            return Feedback.object(hash, Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("未登录", Feedback.STATUS_ERROR);
    }
}
