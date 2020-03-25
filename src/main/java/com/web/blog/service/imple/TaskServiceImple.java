package com.web.blog.service.imple;

import com.web.blog.dao.TaskDao;
import com.web.blog.service.TaskService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.Feedback;

import java.util.List;

@Service
public class TaskServiceImple implements TaskService {
    private final TaskDao taskDao;

    @Autowired
    public TaskServiceImple(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public JSONObject check(String id, String flag, String review) {
        if(taskDao.check(id,flag,review)>0)
        {
            return Feedback.info("课题申请操作成功",Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("课题申请操作失败",Feedback.STATUS_UNKNOWN_ERROR);
    }

    @Override
    public List findstu(String teacher_id,String direction) {
        return taskDao.findstu(teacher_id,direction);
    }
}
