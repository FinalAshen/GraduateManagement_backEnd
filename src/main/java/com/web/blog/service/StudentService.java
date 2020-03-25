package com.web.blog.service;

import com.web.blog.entity.Student;

import java.math.BigDecimal;
import java.util.List;

public interface StudentService {
    public List<Student> findall(String key, int pageNum, int pageSize);

    public void updatepwd(String id, String pwd);

    public void delete(String id);

    public Student findbyid(String id);

    public void updatereply(String id,String flag);

    public boolean createstu(String id,String name,String password,String major,String sclass,String cellphone,String teacher_id);

    public void total(String id, BigDecimal total_result);
}
