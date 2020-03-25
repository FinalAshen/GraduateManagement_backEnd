package com.web.blog.service.imple;

import com.web.blog.dao.TaskDao;
import com.web.blog.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImple implements TaskService {
    private final TaskDao taskDao;

    @Autowired
    public TaskServiceImple(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public void check(String id, String flag, String review) {
        taskDao.check(id,flag,review);
    }

    @Override
    public List findstu(String teacher_id,String direction) {
        return taskDao.findstu(teacher_id,direction);
    }
}
