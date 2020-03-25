package com.web.blog.service.imple;

import com.alibaba.fastjson.JSON;
import com.web.blog.dao.*;
import com.web.blog.service.TeachergroupService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Feedback;

import java.util.List;

@Service
public class TeachergroupServiceImple implements TeachergroupService {
    private final TeachergroupDao teachergroupDao;
    private final SkillmapDao skillmapDao;
    private final TaskDao taskDao;
    private final StudentgroupDao studentgroupDao;

    @Autowired
    public TeachergroupServiceImple(TeachergroupDao teachergroupDao, SkillmapDao skillmapDao, TaskDao taskDao, StudentgroupDao studentgroupDao) {
        this.teachergroupDao = teachergroupDao;
        this.skillmapDao = skillmapDao;
        this.taskDao = taskDao;

        this.studentgroupDao = studentgroupDao;
    }

    @Override
    public JSONObject creategroup(String group_id, String teacher_id, String direction) {
        if (teachergroupDao.creategroup(group_id, teacher_id, direction) > 0) {
            return Feedback.info("创建老师组成功", Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("创建老师组失败", Feedback.STATUS_UNKNOWN_ERROR);
    }

    @Override
    public JSONObject findgroup(String direction) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("findTeagroupbydirectiton", JSON.toJSON(teachergroupDao.findgroup(direction)));
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject fenzu(String name, String group_id) {
        System.out.println(name+group_id);
        List t = skillmapDao.findtea(name);
        for (int i = 0; i < t.size(); i++) {
            List s = taskDao.findstu(t.get(i).toString(), name);
            for (int j = 0; j < s.size(); j++) {
                studentgroupDao.creategroup(group_id, s.get(i).toString(), name);
            }
            teachergroupDao.creategroup(group_id, t.get(i).toString(), name);
        }
        return Feedback.info("分组成功", Feedback.STATUS_SUCCESS);
    }
}
