package com.web.blog.dao;

import com.web.blog.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface StudentDao {
    public List<Student> findall(String key);

    public void updatepwd(@Param("id") String id, @Param("pwd") String pwd);

    public void total(@Param("id") String id, @Param("total_result") BigDecimal total_result);

    public void delete(String id);

    public Student findbyid(String id);

    public void updatereply(@Param("id") String id, @Param("flag") String flag);

    public void createstu(@Param("id") String id, @Param("name") String name, @Param("password") String password, @Param("major") String major, @Param("sclass") String sclass, @Param("cellphone") String cellphone, @Param("teacher_id") String teacher_id);


}
