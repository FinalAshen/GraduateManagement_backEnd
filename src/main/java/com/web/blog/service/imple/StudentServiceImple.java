package com.web.blog.service.imple;

import com.github.pagehelper.PageHelper;
import com.web.blog.dao.StudentDao;
import com.web.blog.entity.Student;
import com.web.blog.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StudentServiceImple implements StudentService {

    private final StudentDao studentDao;

    @Autowired
    public StudentServiceImple(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> findall(String key, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> students;
        if (key == null) {
            students = studentDao.findall("");
        } else {

            students = studentDao.findall(key);
            System.out.println("非空");
        }
        return students;
    }

    @Override
    public void updatepwd(String id, String pwd) {
        studentDao.updatepwd(id, pwd);
    }

    @Override
    public void delete(String id) {
        studentDao.delete(id);
    }

    @Override
    public Student findbyid(String id) {
        return studentDao.findbyid(id);
    }

    @Override
    public void updatereply(String id, String flag) {
        studentDao.updatereply(id, flag);
    }

    @Override
    public boolean createstu(String id, String name, String password, String major, String sclass, String cellphone, String teacher_id) {
        if (studentDao.findbyid(id) == null) {
            studentDao.createstu(id, name, password, major, sclass, cellphone, teacher_id);
            return true;
        }
        return false;
    }

    @Override
    public void total(String id, BigDecimal total_result) {
        studentDao.total(id,total_result);
    }

}
