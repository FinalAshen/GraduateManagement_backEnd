package com.web.blog.service.imple;

import com.github.pagehelper.PageHelper;
import com.web.blog.dao.ApplicationDao;
import com.web.blog.entity.Application;
import com.web.blog.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationServiceImple implements ApplicationService {
    private final ApplicationDao applicationDao;

    @Autowired
    public ApplicationServiceImple(ApplicationDao applicationDao) {
        this.applicationDao = applicationDao;
    }

    @Override
    public List<Application> findall(String key, int pageNum, int pageSize) {
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
        return applications;
    }

    @Override
    public void check(String id, String flag) {
        applicationDao.check(id,flag);
    }
}
