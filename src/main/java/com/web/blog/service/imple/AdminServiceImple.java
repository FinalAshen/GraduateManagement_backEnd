package com.web.blog.service.imple;

import com.web.blog.dao.AdminDao;
import com.web.blog.entity.Admin;
import com.web.blog.service.AdminService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Feedback;

import javax.servlet.http.HttpSession;

@Service
public class AdminServiceImple implements AdminService {
    private final AdminDao adminDao;

    @Autowired
    public AdminServiceImple(AdminDao adminDao) {
        this.adminDao = adminDao;
    }


    @Override
    public JSONObject login(HttpSession session, String id, String password) {
        if (session.getAttribute(AdminService.IDENTITY) != null) {
            session.removeAttribute(AdminService.IDENTITY);
        }
        Admin adm = adminDao.getInfoById(id);
        if (adm != null && adm.getPassword().equals(password)) {
            adm.setPassword(null);
            session.setAttribute(AdminService.IDENTITY, adm);
            return Feedback.info(AdminService.IDENTITY, Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("账号或密码错误", Feedback.STATUS_ERROR);
    }

    @Override
    public JSONObject logout(HttpSession session) {
        if (session.getAttribute(AdminService.IDENTITY) == null) {
            return Feedback.info("未登录", Feedback.STATUS_ERROR);
        }
        session.removeAttribute(AdminService.IDENTITY);
        return Feedback.info("注销成功", Feedback.STATUS_SUCCESS);
    }

}
