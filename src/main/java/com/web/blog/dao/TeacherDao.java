package com.web.blog.dao;

<<<<<<< HEAD
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
=======
import com.web.blog.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface TeacherDao {
    int createTask(@Param("taskInfo") TaskInfo taskInfo, @Param("cycle") Integer cycle);
    int deleteTask(Integer id);
    int updateTask(@Param("taskInfo") TaskInfo taskInfo);
    List<TaskInfo> selectTask(@Param("page") Integer page, @Param("pageSize") Integer pageSize);
    List<StudentInfo> selectStudent(@Param("page") Integer page, @Param("pageSize") Integer pageSize);
    int selectStudentAvailableByTask(@Param("studentID") Integer studentID, @Param("taskID") Integer taskID);
    int selectStudentAvailableByStudent(@Param("studentID") Integer studentID, @Param("teacherID") Integer teacherID);
    int conventStudent(@Param("studentID") Integer studentID, @Param("teacherID") Integer teacherID);
    int isAgreeConventStudent(@Param("studentID") Integer studentID, @Param("teacherID") Integer teacherID);
    int defineTaskForStudent(@Param("taskInfo") TaskInfo taskInfo);
    int givenCharterCreate(@Param("taskID") Integer taskID, @Param("fileName") String fileName);
    List<TeacherGroupInfo> selectOwnTeacherGroup(@Param("teacherID") Integer teacherID, @Param("page") Integer page, @Param("pageSize") Integer pageSize);
    List<StudentGroupInfo> selectOwnStudentGroup(@Param("studentID") Integer studentID, @Param("page") Integer page, @Param("pageSize") Integer pageSize);
    List<TeacherGroupInfo> selectAllTeacherGroup(@Param("page") Integer page, @Param("pageSize") Integer pageSize);
    List<StudentGroupInfo> selectAllStudentGroup(@Param("page") Integer page, @Param("pageSize") Integer pageSize);
    int isExistTopic(@Param("taskID") Integer taskID, @Param("designation") String designation);
    List<Map<String, Object>> selectConventStudent(@Param("teacherID") Integer teacherID, @Param("page") Integer page,
                                 @Param("pageSize") Integer pageSize, @Param("status") Integer status);
    int isExitConventAudit(@Param("studentID") Integer studentID, @Param("teacherID") Integer teacherID);
    int selectStudentAvailableByApplication(@Param("studentID") Integer studentID, @Param("teacherID") Integer teacherID);
    List<ApplicationInfo> isExistStudentAvailableByApplication(@Param("studentID") Integer studentID, @Param("teacherID") Integer teacherID);
    List<TaskInfo> isExistStudentAvailableByTask(Integer taskID);
    List<StudentInfo> isExistStudentAvailableByStudent(Integer studentID);
    List<TaskInfo> isExistStudentAvailableByAllTask(@Param("studentID") Integer studentID, @Param("teacherID") Integer teacherID);
    int cancelStudentByTeacher(Integer studentID);
    int cancelStudentByTask(@Param("studentID") Integer studentID, @Param("teacherID") Integer teacherID);
    List<TeacherInfo> isExistByID(Integer teacherID);
    List<TeacherInfo> isFreezeByID(Integer teacherID);
    TeacherInfo teacherLogin(@Param("teacherID") Integer teacherID, @Param("password") String password);
    int isOwnStudent(@Param("studentID") Integer studentID, @Param("teacherID") Integer teacherID);
    int deleteApplication(@Param("studentID") Integer studentID, @Param("teacherID") Integer teacherID);
    List<StudentInfo> getMyStudent(@Param("teacherID") Integer teacherID, @Param("page") Integer page, @Param("pageSize") Integer pageSize);
    List<Map<String, Object>> getApplyMyStudent(@Param("teacherID") Integer teacherID, @Param("page") Integer page, @Param("pageSize") Integer pageSize);
    List<Map<String, Object>> getInviteStudent(@Param("teacherID") Integer teacherID, @Param("page") Integer page, @Param("pageSize") Integer pageSize);
    List<Map<String, Object>> getHasAcceptStudent(@Param("teacherID") Integer teacherID, @Param("page") Integer page, @Param("pageSize") Integer pageSize);
    int upSAgreeOwnStudent(@Param("teacherID") Integer teacherID, @Param("studentID") Integer studentID);
    int upAgreeOwnStudent(@Param("teacherID") Integer teacherID, @Param("studentID") Integer studentID);

>>>>>>> newItem
    public List<Teacher> findall(@Param("key") String key, @Param("page") int page, @Param("pageSize") int pageSize);

    public int getTeacherSum(@Param("key")String key);

    public int updatepwd(@Param("id") String id, @Param("pwd") String pwd);

    public int delete(String id);

    public Teacher findbyid(String id);

    public int createtea(@Param("id") String id, @Param("name") String name, @Param("password") String password, @Param("cellphone") String cellphone);

    List<Teacher> getTeacher(@Param("skill") String skill, @Param("page") int page, @Param("pageSize") int pageSize);

    List<Skill_map> getSkill(List<Teacher> teachers);

    List<Teacher> getMyTeacher(int student_id);

    int getTeacherCount(@Param("skill") String skill);
}
