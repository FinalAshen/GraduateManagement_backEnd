package com.web.blog.service.imple;

import com.web.blog.dao.UserDao;
import com.web.blog.entity.UserInfo;
import com.web.blog.service.UserService;
import com.web.blog.service.VisitorService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Feedback;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Service
public class VisitorServiceImple implements VisitorService {
    private final UserDao userDao;

    @Autowired
    public VisitorServiceImple(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public JSONObject register(HttpSession session, UserInfo user) {

        String account = null;
        boolean isUnique = false;
        synchronized (this) {
            if (userDao.getInfoByEmail(user.getEmail()) == 0) {
                while (!isUnique) {
                    account = getUUId();
                    if (account.length() < 6 || account.length() > 13) {
                        continue;
                    }
                    user.setAccount(account);
                    if (userDao.getInfoByAccount(user.getAccount()) == null) {
                        isUnique = true;
                    }
                }
                userDao.doRegister(user);
                session.setAttribute(UserService.IDENTITY, user);
                return Feedback.info(user.getAccount(), Feedback.STATUS_SUCCESS);
            }
            return Feedback.info("邮箱已被注册", Feedback.STATUS_ERROR);
        }
    }
    public static String getUUId() {
        int hashCode = UUID.randomUUID().toString().hashCode();
        hashCode = hashCode > 0 ? hashCode : -hashCode;
        String orderId= String.format("%d", hashCode);
        return orderId;
    }
}
