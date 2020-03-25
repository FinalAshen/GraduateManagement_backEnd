package com.web.blog.dao;

import com.web.blog.entity.Application;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationDao {
    public List<Application> findall(String key);
    public void check(@Param("id")String id,@Param("flag")String flag);
    public int applyApplication(@Param("student_id") int student_id,@Param("teacher_id") int teacher_id);
    public int if_exsist(int student_id);
    public List<Application> check_application(int student_id);
    public int cancelMyApplication(@Param("id") int id,@Param("student_id") int student_id);
    public int choiceTask(@Param("student_id") int student_id,@Param("teacher_id") int teacher_id,@Param("task_id") int task_id);
    public int accept_application(int application_id);
    public int refuse_application(int application_id);
}
