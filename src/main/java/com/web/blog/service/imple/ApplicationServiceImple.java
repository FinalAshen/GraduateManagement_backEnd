package com.web.blog.service.imple;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.web.blog.dao.ApplicationDao;
import com.web.blog.entity.Application;
import com.web.blog.service.ApplicationService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Feedback;

import java.util.List;

@Service
public class ApplicationServiceImple implements ApplicationService {
    private final ApplicationDao applicationDao;

    @Autowired
    public ApplicationServiceImple(ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }

    @Override
    public JSONObject findall(String key, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Application> applications;
        if(key==null)
        {
            applications=applicationDao.findall("");
        }
        else
        {
            applications=applicationDao.findall(key);
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("getApplications", JSON.toJSON(applications));
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject check(String id, String flag) {
        if(applicationDao.check(id,flag)>0)
        {
            return Feedback.info("处理申请成功",Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("处理申请失败",Feedback.STATUS_UNKNOWN_ERROR);
    }
}
