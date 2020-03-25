package com.web.blog.service.imple;

import com.web.blog.dao.TeachergroupDao;
import com.web.blog.service.TeachergroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeachergroupServiceImple implements TeachergroupService {
    private final TeachergroupDao teachergroupDao;

    @Autowired
    public TeachergroupServiceImple(TeachergroupDao teachergroupDao) {
        this.teachergroupDao = teachergroupDao;
    }

    @Override
    public void creategroup(String group_id, String teacher_id,String direction) {
        teachergroupDao.creategroup(group_id, teacher_id,direction);
    }

    @Override
    public List findgroup(String direction) {
        return teachergroupDao.findgroup(direction);
    }
}
