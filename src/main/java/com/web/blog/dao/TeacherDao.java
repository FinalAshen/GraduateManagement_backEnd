package com.web.blog.dao;

import com.web.blog.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.web.blog.entity.Skill_map;
import com.web.blog.entity.Teacher;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.List;
@Repository
public interface TeacherDao {
    public List<Teacher> findall(String key);

    public int updatepwd(@Param("id") String id, @Param("pwd") String pwd);

    public int delete(String id);

    public Teacher findbyid(String id);

    public int createtea(@Param("id")String id,@Param("name")String name,@Param("password")String password,@Param("cellphone")String cellphone);

    List<Teacher> getTeacher(@Param("skill")String skill,@Param("page")int page,@Param("pageSize")int pageSize);
    List<Skill_map> getSkill(List<Teacher> teachers);
    List<Teacher> getMyTeacher(int student_id);
    int getTeacherCount(@Param("skill")String skill);
}
