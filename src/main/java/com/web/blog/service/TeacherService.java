package com.web.blog.service;

import com.web.blog.entity.Teacher;

import java.util.List;

public interface TeacherService {
    public List<Teacher> findall(String key, int pageNum, int pageSize);

    public void updatepwd(String id, String pwd);

    public void delete(String id);

    public Teacher findbyid(String id);

    public boolean createtea(String id,String name,String password,String cellphone);
}
