package com.web.blog.service;

import com.web.blog.entity.Teacher;
import org.json.JSONObject;

import java.util.List;

public interface TeacherService {
    JSONObject findall(int pageNum, int pageSize,String key);

    JSONObject updatepwd(String id, String pwd);

    JSONObject delete(String id);

    JSONObject findbyid(String id);

    JSONObject createtea(String id,String name,String password,String cellphone);
}
