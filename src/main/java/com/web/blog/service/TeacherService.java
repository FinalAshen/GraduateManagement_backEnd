package com.web.blog.service;

<<<<<<< HEAD
import com.web.blog.entity.Teacher;
import org.json.JSONObject;

import java.util.List;

public interface TeacherService {
=======
import com.web.blog.entity.StudentInfo;
import com.web.blog.entity.TaskInfo;
import com.web.blog.entity.TeacherInfo;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface TeacherService {
    final static String IDENTITY = "Teacher";

    JSONObject login(HttpSession session, TeacherInfo teacherInfo);
    JSONObject getInfo(HttpSession session);
    /**
     *  创建课题
     *  body: {
     *      designation: 名称;
     *      direction: 方向;
     *      teacher_id: 教师工号;
     *      status: 0 审核(默认 无需传)
     *      cycle: 生命周期(整数)
     *      *crt_ts: 创建时间(默认)
     *      *lm_ts： 结束时间 => crt_ts + cycle
     *  }
     *  @date 2020/3/20
     * **/
    JSONObject createTask(HttpSession session, TaskInfo taskInfo);
    /**
     *  删除课题
     *  params: {
     *      id: 课题编号
     *  }
     *  @date 2020/3/20
     * */
    JSONObject deleteTask(HttpSession session, Integer id);
    /**
     *  更新课题
     *  body: {
     *      designation: 名称;
     *      direction: 方向;
     *      teacher_id: 教师工号;
     *      status: 0 审核(默认 无需传)
     *      cycle: 生命周期(整数)
     *      *crt_ts: 创建时间(默认)
     *      *lm_ts： 结束时间 => crt_ts + endTime
     *  }
     *  @date 2020/3/20
     * */
    JSONObject updateTask(HttpSession session, TaskInfo taskInfo);
    /**
     *  查询课题列表
     *  params: {
     *      page: 页数
     *      pageSize: 显示条数 (-1: 查询全部 => Sum)
     *  }
     *  @date 2020/3/20
     * */
    JSONObject selectTask(Integer page, Integer pageSize);
    /**
     *  查询学生列表
     *  params: {
     *      page: 页数
     *      pageSize: 显示条数 (-1: 查询全部 => Sum)
     *  }
     *  @date 2020/3/20
     * */
    JSONObject selectStudent(Integer page, Integer pageSize);
    JSONObject agreeOwnStudent(HttpSession session, Integer studentID);
    /**
     *  查询所有指定目标学生
     *  params: {
     *      teacherID: 教师工号
     *      page: 页数
     *      pageSize: 单页容量
     *      status: 查询学生的状态
     *  }
     * **/
    JSONObject selectConventStudent(HttpSession session, Integer teacherID, Integer page, Integer pageSize, Integer status);
    /**
     *  指定目标学生
     *  params: {
     *      studentID: 学生学号
     *      teacherID: 教师工号
     *  }
     * **/
    JSONObject conventStudent(HttpSession session, Integer studentID, Integer teacherID);
    /**
     *  为指定学生定义课题
     *  body: {
     *      designation: 名称;
     *      direction: 方向;
     *      teacher_id: 教师工号;
     *      status: 0 审核(默认 无需传)
     *      cycle: 生命周期(整数)
     *      *crt_ts: 创建时间(默认)
     *      *lm_ts： 结束时间 => crt_ts + endTime
     *      studentID: 学生学号
     *  }
     * **/
    JSONObject defineTaskForStudent(HttpSession session, TaskInfo taskInfo);
    /**
     *  同意当前学生选择目标课题
     *  body: {
     *      stuID: 学生学号;
     *      taskID: 课题编号;
     *  }
     **/
    JSONObject selectStudentAvailable(HttpSession session, TaskInfo taskInfo);
    /**
     *  取消当前学生选择目标课题
     *  body: {
     *      stuID: 学生学号;
     *      taskID: 课题编号;
     *  }
     *  @date 2020/3/20
     * */
    JSONObject selectStudentCancel(HttpSession session, TaskInfo taskInfo);
    /**
     *  上传任务书文件
     *  body: {
     *      attachs: 文件;
     *      taskID: 课题编号
     *  }
     *  @date 2020/3/20
     * */
    JSONObject givenCharterCreate(HttpSession session, MultipartFile[] attachs, Integer taskID, HttpServletRequest request);
    /**
     *  更新任务书文件
     *  body: {
     *      attachs: 文件;
     *      charter: 任务书路径;
     *      taskID: 课题编号;
     *  }
     *  @date 2020/3/20
     * */
    JSONObject givenCharterUpdate(HttpSession session, MultipartFile[] attachs, TaskInfo taskInfo, HttpServletRequest request);
    /**
     *  删除任务书文件
     *  params: {
     *      fileName: 任务书路径(唯一性)
     *  }
     *  @date 2020/3/20
     * */
    Boolean givenCharterDelete(HttpSession session, Integer taskID, String fileName, HttpServletRequest request);
    /**
     *  查询教师组(个人|集体)
     *  params: {
     *      page: 页数;
     *      pageSize: 单页数量
     *      flag: 标记(-1: 查询自身教师组; 其他: 查询全部)
     *  }
     *  @date 2020/3/20
     * */
    JSONObject showGroupTeacher(Integer teacherID, Integer page, Integer pageSize, Integer flag);
    /**
     *  查询学生组(个人|集体)
     *  params: {
     *      page: 页数;
     *      pageSize: 单页数量
     *      flag: 标记(-1: 查询自身教师组所带的学生组; 其他: 查询全部)
     *  }
     *  @date 2020/3/20
     * */
    JSONObject showGroupStudent(Integer studentID, Integer page, Integer pageSize, Integer flag);
    JSONObject getMyStudent(HttpSession session, Integer page, Integer pageSize);
    JSONObject getApplyMyStudent(HttpSession session, Integer page, Integer pageSize);
    JSONObject getInviteStudent(HttpSession session, Integer page, Integer pageSize);
    JSONObject getHasAcceptStudent(HttpSession session, Integer page, Integer pageSize);

>>>>>>> newItem
    JSONObject findall(int pageNum, int pageSize,String key);

    JSONObject updatepwd(String id, String pwd);

    JSONObject delete(String id);

    JSONObject findbyid(String id);

    JSONObject createtea(String id,String name,String password,String cellphone);
}
