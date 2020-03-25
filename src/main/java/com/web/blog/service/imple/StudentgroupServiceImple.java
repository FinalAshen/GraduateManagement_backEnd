package com.web.blog.service.imple;

import com.web.blog.dao.StudentgroupDao;
import com.web.blog.service.StudentgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentgroupServiceImple implements StudentgroupService {
    private final StudentgroupDao studentgroupDao;

    @Autowired
    public StudentgroupServiceImple(StudentgroupDao studentgroupDao) {
        this.studentgroupDao = studentgroupDao;
    }

    @Override
    public void creategroup(String group_id, String student_id, String direction) {
        studentgroupDao.creategroup(group_id, student_id, direction);
    }

    @Override
    public List findgroup(String direction) {
        return studentgroupDao.findgroup(direction);
    }
}
