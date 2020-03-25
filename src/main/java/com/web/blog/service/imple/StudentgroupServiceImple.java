package com.web.blog.service.imple;

import com.alibaba.fastjson.JSON;
import com.web.blog.dao.StudentgroupDao;
import com.web.blog.service.StudentgroupService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Feedback;

import java.util.List;

@Service
public class StudentgroupServiceImple implements StudentgroupService {
    private final StudentgroupDao studentgroupDao;

    @Autowired
    public StudentgroupServiceImple(StudentgroupDao studentgroupDao) {
        this.studentgroupDao = studentgroupDao;
    }

    @Override
    public JSONObject creategroup(String group_id, String student_id, String direction) {
        if( studentgroupDao.creategroup(group_id, student_id, direction)>0)
        {
            return Feedback.info("创建学生组成功",Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("创建学生组失败",Feedback.STATUS_UNKNOWN_ERROR);
    }

    @Override
    public JSONObject findgroup(String direction) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("findStugroupbydirectiton", JSON.toJSON(studentgroupDao.findgroup(direction)));
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }


}
