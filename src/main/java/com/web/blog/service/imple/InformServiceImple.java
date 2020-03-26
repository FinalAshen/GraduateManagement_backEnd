package com.web.blog.service.imple;

import com.alibaba.fastjson.JSON;
import com.web.blog.dao.InformDao;
import com.web.blog.entity.Admin;
import com.web.blog.entity.Inform;
import com.web.blog.service.AdminService;
import com.web.blog.service.InformService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Feedback;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class InformServiceImple implements InformService {
    private final InformDao informDao;

    @Autowired
    public InformServiceImple(InformDao informDao) {
        this.informDao = informDao;
    }

    @Override
    public JSONObject findall() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("getInforms", JSON.toJSON(informDao.findall()));
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject createinfo(String title, String content, String creator, String begin_ts, String end_ts, String t_group, String s_group) {
//        HttpSession session = null;
//        Admin admin=(Admin) session.getAttribute(AdminService.IDENTITY);
//        String creators=admin.getName();
//        System.out.println(creators);
        if (informDao.createinfo(title, content, creator, begin_ts, end_ts, t_group, s_group) > 0) {
            return Feedback.info("发布通知成功", Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("发布通知失败", Feedback.STATUS_UNKNOWN_ERROR);
    }
}
