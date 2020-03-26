package com.web.blog.dao;

import com.web.blog.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import com.web.blog.entity.Skill_map;
import com.web.blog.entity.Student;
import com.web.blog.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentDao {
    public List<Student> findall(@Param("key") String key,@Param("page")int page,@Param("pageSize")int pageSize);

    public int getStudentSum(String key);

    public int updatepwd(@Param("id") String id, @Param("pwd") String pwd);

    public int total(@Param("id") String id, @Param("total_result") BigDecimal total_result);

    public int delete(String id);

    public Student findbyid(String id);

    public int updatereply(@Param("id") String id, @Param("flag") String flag);

    public int createstu(@Param("id") String id, @Param("name") String name, @Param("password") String password, @Param("major") String major, @Param("sclass") String sclass, @Param("cellphone") String cellphone, @Param("teacher_id") String teacher_id);

    Student studentLogin(@Param("id") int id,@Param("password") String password);
    int isExistByID(@Param("id") int id);
    int isFreezeByID(@Param("id") int id);
    Student checkMyResult(int student_id);
    Student getInfo(int student_id);
    int changeInfo(@Param("student_id") int student_id,@Param("cellphone") int cellphone);
    int changePassword(@Param("student_id") int student_id,@Param("old_password") String old_password,@Param("password") String password);

}
