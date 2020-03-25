package com.web.blog.service.imple;

import com.github.pagehelper.PageHelper;
import com.web.blog.dao.TeacherDao;
import com.web.blog.entity.Student;
import com.web.blog.entity.Teacher;
import com.web.blog.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherDao teacherDao;

    @Autowired
    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    @Override
    public List<Teacher> findall(String key, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Teacher> teachers;
        if (key == null) {
            teachers = teacherDao.findall("");
        } else {
            teachers = teacherDao.findall(key);
        }
        return teachers;
    }

    @Override
    public void updatepwd(String id, String pwd) {
        System.out.println(id+pwd+"service");
        teacherDao.updatepwd(id,pwd);
    }

    @Override
    public void delete(String id) {
        teacherDao.delete(id);
    }

    @Override
    public Teacher findbyid(String id) {
        return teacherDao.findbyid(id);
    }

    @Override
    public boolean createtea(String id, String name, String password, String cellphone) {
        System.out.println(id+name+password+cellphone);
        System.out.println(teacherDao.findbyid(id));
        if(teacherDao.findbyid(id)==null)
        {
            teacherDao.createtea(id,name,password,cellphone);
            return true;
        }
        return false;
    }
}
