package com.web.blog.dao;

import com.web.blog.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherDao {
    public List<Teacher> findall(String key);

    public void updatepwd(@Param("id") String id, @Param("pwd") String pwd);

    public void delete(String id);

    public Teacher findbyid(String id);

    public void createtea(@Param("id")String id,@Param("name")String name,@Param("password")String password,@Param("cellphone")String cellphone);
}
