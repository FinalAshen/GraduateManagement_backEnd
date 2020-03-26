package com.web.blog.service.imple;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.web.blog.dao.TeacherDao;
import com.web.blog.entity.Student;
import com.web.blog.entity.Teacher;
import com.web.blog.service.TeacherService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Feedback;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherDao teacherDao;

    @Autowired
    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public JSONObject findall(int pageSize,int pageCurrent,String key) {
        int sumPage;
        List<Teacher> teachers;
        if (key == null) {
            sumPage=teacherDao.getTeacherSum("");
            teachers = teacherDao.findall("",pageCurrent,pageSize);
        } else {
            sumPage=teacherDao.getTeacherSum(key);
            teachers = teacherDao.findall(key,pageCurrent,pageSize);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("getTeachers", JSON.toJSON(teachers));
        jsonObject.put("teacherSumPage",sumPage);
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }


    @Override
    public JSONObject updatepwd(String id, String pwd) {
        if (teacherDao.updatepwd(id, pwd) > 0) {
            return Feedback.info("修改老师密码成功", Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("修改老师密码失败功", Feedback.STATUS_UNKNOWN_ERROR);
    }

    @Override
    public JSONObject delete(String id) {
        if (teacherDao.delete(id)>0)
        {
            return Feedback.info("删除老师成功", Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("删除老师失败",Feedback.STATUS_UNKNOWN_ERROR);
    }

    @Override
    public JSONObject findbyid(String id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("getTeacherbyid", JSON.toJSON(teacherDao.findbyid(id)));
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject createtea(String id, String name, String password, String cellphone) {
        if (teacherDao.findbyid(id) == null) {
            teacherDao.createtea(id, name, password, cellphone);
            return Feedback.info("创建老师成功", Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("创建老师失败", Feedback.STATUS_UNKNOWN_ERROR);
    }
}
