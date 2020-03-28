package com.web.blog.service.imple;

import com.alibaba.fastjson.JSON;
import com.web.blog.dao.TeacherDao;
import com.web.blog.entity.*;
import com.web.blog.service.TeacherService;
import com.web.blog.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import utils.Feedback;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class TeacherServiceImple implements TeacherService {
    private final TeacherDao teacherDao;
    private static final double FILE_SIZE = 51200000;

    @Autowired
    public TeacherServiceImple(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }
    private Boolean checkIdentity(HttpSession session) {
        if (session.getAttribute(TeacherService.IDENTITY) == null) {
            return false;
        }
        return true;
    }

    @Override
    public JSONObject login(HttpSession session, TeacherInfo teacherInfo) {
        if (teacherDao.isExistByID(teacherInfo.getId()).size() == 0) {
            return Feedback.info("账号不存在", Feedback.STATUS_ERROR);
        }
        if (teacherDao.isFreezeByID(teacherInfo.getId()).size() > 0) {
            return Feedback.info("账号已被冻结", Feedback.STATUS_ERROR);
        }
        if (session.getAttribute(TeacherService.IDENTITY) != null) {
            session.removeAttribute(TeacherService.IDENTITY);
        }
        TeacherInfo teacher = teacherDao.teacherLogin(teacherInfo.getId(), teacherInfo.getPassword());
        if (teacher != null) {
            teacher.setPassword(null);
            session.setAttribute(TeacherService.IDENTITY, teacher);
            return Feedback.info("登录成功", Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("登录失败", Feedback.STATUS_UNKNOWN_ERROR);
    }

    @Override
    public JSONObject getInfo(HttpSession session) {
        if (!checkIdentity(session)) {
            return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);
        }
        TeacherInfo teacher = (TeacherInfo) session.getAttribute(TeacherService.IDENTITY);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userInfo", JSON.toJSON(teacher));
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject createTask(HttpSession session, TaskInfo taskInfo) {
        if (!checkIdentity(session)) {
            return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);
        }
        if (teacherDao.isExistTopic(taskInfo.getTeacher_id(), taskInfo.getDesignation()) > 0) {
            return Feedback.info("标题已存在", Feedback.STATUS_ERROR);
        }
        if (teacherDao.createTask(taskInfo, taskInfo.getCycle()) > 0) {
            return Feedback.info("创建课题成功", Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("创建课题失败", Feedback.STATUS_UNKNOWN_ERROR);
    }

    @Override
    public JSONObject deleteTask(HttpSession session, Integer id) {
        if (!checkIdentity(session)) {
            return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);
        }
        if (teacherDao.deleteTask(id) > 0) {
            return Feedback.info("删除课题成功", Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("删除课题失败", Feedback.STATUS_UNKNOWN_ERROR);
    }

    @Override
    public JSONObject updateTask(HttpSession session, TaskInfo taskInfo) {
        if (!checkIdentity(session)) {
            return Feedback.info("你没有权限", Feedback.STATUS_ACCESS_FORBID);
        }
        if (teacherDao.isExistTopic(taskInfo.getId(), taskInfo.getDesignation()) > 0) {
            return Feedback.info("标题已存在", Feedback.STATUS_ERROR);
        }
        if (teacherDao.updateTask(taskInfo) > 0) {
            return Feedback.info("更新课题成功", Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("更新课题失败", Feedback.STATUS_UNKNOWN_ERROR);
    }

    @Override
    public JSONObject selectTask(Integer page, Integer pageSize) {
        List<TaskInfo> lists = teacherDao.selectTask(0, -1);
        int sumTask = lists.size();
        lists = teacherDao.selectTask(page, pageSize);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        lists.forEach(obj -> jsonArray.put(JSON.toJSON(obj)));
        jsonObject.put("TaskList", jsonArray);
        jsonObject.put("SumTask", sumTask);
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject selectStudent(Integer page, Integer pageSize) {
        List<StudentInfo> lists = teacherDao.selectStudent(0, -1);
        int sumStudents = lists.size();
        lists = teacherDao.selectStudent(page, pageSize);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        lists.forEach(obj -> jsonArray.put(JSON.toJSON(obj)));
        jsonObject.put("StudentList", jsonArray);
        jsonObject.put("SumPage", sumStudents);
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject agreeOwnStudent(HttpSession session, Integer studentID) {
        if (!checkIdentity(session)) {
            return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);
        }
        TeacherInfo teacher = (TeacherInfo) session.getAttribute(TeacherService.IDENTITY);
        teacherDao.upSAgreeOwnStudent(teacher.getId(), studentID);
        teacherDao.upAgreeOwnStudent(teacher.getId(), studentID);
        return Feedback.info("执行成功", Feedback.STATUS_SUCCESS);
    }

    /// 查询教师自选学生
    @Override
    public JSONObject selectConventStudent(HttpSession session, Integer teacherID, Integer page, Integer pageSize, Integer status) {
        List<Map<String, Object>> map = teacherDao.selectConventStudent(teacherID, 0, -1, status);
        int sumStudents = map.size();
        map = teacherDao.selectConventStudent(teacherID, page, pageSize, status);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        map.forEach(res -> jsonArray.put(JSON.toJSON(res)));
        jsonObject.put("ConventStudentList", jsonArray);
        jsonObject.put("SumPage", sumStudents);
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    /// 教师向学生发出邀请  流程: 先邀请后发布课题
    @Override
    public JSONObject conventStudent(HttpSession session, Integer studentID, Integer teacherID) {
        if (!checkIdentity(session)) {
            return Feedback.info("你没有权限", Feedback.STATUS_ACCESS_FORBID);
        }
        if (teacherDao.isExitConventAudit(studentID, teacherID) > 0) {
            return Feedback.info("已经向目标学生发出邀请并等待目标学生的同意", Feedback.STATUS_ERROR);
        }
        if (teacherDao.conventStudent(studentID, teacherID) > 0) {
            return Feedback.info("邀请目标学生成功", Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("邀请目标学生失败", Feedback.STATUS_ERROR);
    }

    @Override
    public JSONObject defineTaskForStudent(HttpSession session, TaskInfo taskInfo) {
        if (!checkIdentity(session)) {
            return Feedback.info("你没有权限", Feedback.STATUS_ACCESS_FORBID);
        }
        int count = teacherDao.isAgreeConventStudent(taskInfo.getStudent_id(), taskInfo.getTeacher_id());
        teacherDao.selectStudentAvailableByStudent(taskInfo.getStudent_id(), taskInfo.getTeacher_id());
        if (teacherDao.isAgreeConventStudent(taskInfo.getStudent_id(), taskInfo.getTeacher_id()) > 0 &&
        teacherDao.defineTaskForStudent(taskInfo) > 0) {
            return Feedback.info("成功为目标学生发布课题", Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("失败为目标学生发布课题", Feedback.STATUS_ERROR);
    }

    /// 目标教师同意学生选择目标课题
    @Override
    public JSONObject selectStudentAvailable(HttpSession session, TaskInfo taskInfo) {
        if (!checkIdentity(session)) {
            return Feedback.info("你没有权限", Feedback.STATUS_ACCESS_FORBID);
        }
        if (teacherDao.isExistStudentAvailableByApplication(taskInfo.getStudent_id(), taskInfo.getTeacher_id()).size() == 0) {
            return Feedback.info("学生未向您发出请求", Feedback.STATUS_ACCESS_FORBID);
        }
        List<TaskInfo> l = teacherDao.isExistStudentAvailableByTask(taskInfo.getId());
        for (int i = 0; i < l.size(); ++i) {
            if (l.get(i).getStudent_id() != 0) {
                return Feedback.info("当前课题已被其他学生选择", Feedback.STATUS_ACCESS_FORBID);
            }
        }
        List<StudentInfo> p = teacherDao.isExistStudentAvailableByStudent(taskInfo.getStudent_id());
        for (int i = 0; i < p.size(); ++i) {
            if (p.get(i).getTeacher_id() != 0) {
                return Feedback.info("当前学生已经选择导师", Feedback.STATUS_ACCESS_FORBID);
            }
        }
        int count = teacherDao.isOwnStudent(taskInfo.getStudent_id(), taskInfo.getTeacher_id());
        System.out.println(count);
        teacherDao.selectStudentAvailableByApplication(taskInfo.getStudent_id(), taskInfo.getTeacher_id());
        teacherDao.selectStudentAvailableByStudent(taskInfo.getStudent_id(), taskInfo.getTeacher_id());
        if (count > 0) {
            teacherDao.selectStudentAvailableByApplication(taskInfo.getStudent_id(), taskInfo.getTeacher_id());
            return Feedback.info("选择学生成功", Feedback.STATUS_SUCCESS);
        }
        teacherDao.selectStudentAvailableByTask(taskInfo.getStudent_id(), taskInfo.getId());
        return Feedback.info("学生选课成功", Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject selectStudentCancel(HttpSession session, TaskInfo taskInfo) {
        if (!checkIdentity(session)) {
            return Feedback.info("你没有权限", Feedback.STATUS_ACCESS_FORBID);
        }
        if (teacherDao.isExistStudentAvailableByApplication(taskInfo.getStudent_id(), taskInfo.getTeacher_id()).size() == 0) {
            return Feedback.info("师生之间不存在或已否决申请关系", Feedback.STATUS_ACCESS_FORBID);
        }
        teacherDao.deleteApplication(taskInfo.getStudent_id(), taskInfo.getTeacher_id());
        StudentInfo p = teacherDao.isExistStudentAvailableByStudent(taskInfo.getStudent_id()).get(0);
        if (p.getTeacher_id() == taskInfo.getTeacher_id()) {
            teacherDao.cancelStudentByTeacher(taskInfo.getStudent_id());
        }
        List<TaskInfo> w = teacherDao.isExistStudentAvailableByAllTask(taskInfo.getStudent_id(), taskInfo.getTeacher_id());
        for (int i = 0; i < w.size(); ++i) {
            if (w.get(i).getStudent_id() == taskInfo.getStudent_id()) {
                teacherDao.cancelStudentByTask(taskInfo.getStudent_id(), taskInfo.getTeacher_id());
                break;
            }
        }
        return Feedback.info("执行成功", Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject givenCharterCreate(HttpSession session, MultipartFile[] attachs, Integer taskID, HttpServletRequest request) {
        if (!checkIdentity(session)) {
            return Feedback.info("你没有权限", Feedback.STATUS_ACCESS_FORBID);
        }
        String filePath = request.getServletContext().getRealPath("/") + "statics/upload";
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        List<String> lis = new ArrayList<>();
        HashMap<String, String> hashMap = new HashMap<>();
        for (MultipartFile file: attachs) {
            String oldFileName = file.getOriginalFilename();
            String prefix = FilenameUtils.getExtension(oldFileName).toLowerCase();
            String fileName = System.currentTimeMillis() + Math.random() + "." + prefix;
            if(file.isEmpty()) {
                hashMap.put(oldFileName, "文件不存在");
                continue;
            }
            if (!("docx".equals(prefix) || "doc".equals(prefix))) {
                hashMap.put(oldFileName, "文件格式错误");
                continue;
            }
            if (file.getSize() > FILE_SIZE) {
                hashMap.put(oldFileName, "上传文件大小不得超过50M");
                continue;
            }
            try {
                File tarfile = new File(filePath, fileName);
                if(!tarfile.exists()){
                    tarfile.mkdirs();
                }
                file.transferTo(tarfile);
                teacherDao.givenCharterCreate(taskID, fileName);
            } catch (IOException e) {
                hashMap.put(oldFileName, "文件上传失败");
                continue;
            }
            hashMap.put(fileName, "文件上传成功");
            break;
        }
        jsonArray.put(JSON.toJSON(hashMap));
        jsonObject.put("fileUpList", jsonArray);
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject givenCharterUpdate(HttpSession session, MultipartFile[] attachs, TaskInfo taskInfo, HttpServletRequest request) {
        TeacherInfo teacherInfo = (TeacherInfo)session.getAttribute(TeacherService.IDENTITY);
        if (givenCharterDelete(session, taskInfo.getId(), taskInfo.getCharter(), request)) {
            return givenCharterCreate(session, attachs, taskInfo.getId(), request);
        }
        return Feedback.info("更新失败", Feedback.STATUS_ERROR);
    }

    @Override
    public Boolean givenCharterDelete(HttpSession session, Integer taskID, String filaName, HttpServletRequest request) {
        if (!checkIdentity(session)) {
            return false;
        }
        String filePath = request.getServletContext().getRealPath("/") + "statics/upload/" + filaName;
        File file = new File(filePath);
        if (file.isFile() && file.delete() && teacherDao.givenCharterCreate(taskID, "") > 0) {
            return true;
        }
        return false;
    }

    @Override
    public JSONObject showGroupTeacher(Integer teacherID, Integer page, Integer pageSize, Integer flag) {
        List<TeacherGroupInfo> lists = null;
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        int sumNumber;
        // 查询自己所在的组
        if (flag == -1) {
            sumNumber = teacherDao.selectOwnTeacherGroup(teacherID, 0, -1).size();
            lists = teacherDao.selectOwnTeacherGroup(teacherID, page, pageSize);
        } else {
            sumNumber = teacherDao.selectAllTeacherGroup(0, -1).size();
            lists = teacherDao.selectAllTeacherGroup(page, pageSize);
        }
        lists.forEach(obj -> jsonArray.put(JSON.toJSON(obj)));
        jsonObject.put("teacherGroupList", jsonArray);
        jsonObject.put("SumNumber", sumNumber);
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject showGroupStudent(Integer studentID, Integer page, Integer pageSize, Integer flag) {
        List<StudentGroupInfo> lists = null;
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        int sumNumber;
        // 查询自己所在的组
        if (flag == -1) {
            sumNumber = teacherDao.selectOwnStudentGroup(studentID, 0, -1).size();
            lists = teacherDao.selectOwnStudentGroup(studentID, page, pageSize);
        } else {
            sumNumber = teacherDao.selectAllStudentGroup(0, -1).size();
            lists = teacherDao.selectAllStudentGroup(page, pageSize);
        }
        lists.forEach(obj -> jsonArray.put(JSON.toJSON(obj)));
        jsonObject.put("studentGroupList", jsonArray);
        jsonObject.put("SumNumber", sumNumber);
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject getMyStudent(HttpSession session, Integer page, Integer pageSize) {
        if (!checkIdentity(session)) {
            return Feedback.info("你没有权限", Feedback.STATUS_ACCESS_FORBID);
        }
        TeacherInfo teacher = (TeacherInfo) session.getAttribute(TeacherService.IDENTITY);
        System.out.println(teacher.getId());
        List<StudentInfo> lists = teacherDao.getMyStudent(teacher.getId(), 0, -1);
        int sumStudents = lists.size();
        lists = teacherDao.getMyStudent(teacher.getId(), page, pageSize);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        lists.forEach(obj -> jsonArray.put(JSON.toJSON(obj)));
        jsonObject.put("MyStudentList", jsonArray);
        jsonObject.put("SumPage", sumStudents);
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject getApplyMyStudent(HttpSession session, Integer page, Integer pageSize) {
        if (!checkIdentity(session)) {
            return Feedback.info("你没有权限", Feedback.STATUS_ACCESS_FORBID);
        }
        TeacherInfo teacher = (TeacherInfo) session.getAttribute(TeacherService.IDENTITY);
        List<Map<String, Object>> map = teacherDao.getApplyMyStudent(teacher.getId(), 0, -1);
        int SumPage = map.size();
        map = teacherDao.getApplyMyStudent(teacher.getId(), page, pageSize);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        map.forEach(res -> jsonArray.put(JSON.toJSON(res)));
        jsonObject.put("ApplyMyStudent", jsonArray);
        jsonObject.put("SumPage", SumPage);
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject getInviteStudent(HttpSession session, Integer page, Integer pageSize) {
        if (!checkIdentity(session)) {
            return Feedback.info("你没有权限", Feedback.STATUS_ACCESS_FORBID);
        }
        TeacherInfo teacher = (TeacherInfo) session.getAttribute(TeacherService.IDENTITY);
        List<Map<String, Object>> map = teacherDao.getInviteStudent(teacher.getId(), 0, -1);
        int SumPage = map.size();
        map = teacherDao.getInviteStudent(teacher.getId(), page, pageSize);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        map.forEach(res -> jsonArray.put(JSON.toJSON(res)));
        jsonObject.put("MyInviteStudent", jsonArray);
        jsonObject.put("SumPage", SumPage);
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject getHasAcceptStudent(HttpSession session, Integer page, Integer pageSize) {
        if (!checkIdentity(session)) {
            return Feedback.info("你没有权限", Feedback.STATUS_ACCESS_FORBID);
        }
        TeacherInfo teacher = (TeacherInfo) session.getAttribute(TeacherService.IDENTITY);
        List<Map<String, Object>> map = teacherDao.getHasAcceptStudent(teacher.getId(), 0, -1);
        int SumPage = map.size();
        map = teacherDao.getHasAcceptStudent(teacher.getId(), page, pageSize);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        map.forEach(res -> jsonArray.put(JSON.toJSON(res)));
        jsonObject.put("HasAcceptStudent", jsonArray);
        jsonObject.put("SumPage", SumPage);
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject findall(int pageSize,int pageCurrent,String key) {
        int sumPage;
        List<Teacher> teachers;
        if (key == null) {
            sumPage=teacherDao.getTeacherSum("");
            teachers = teacherDao.findall("",pageCurrent,pageSize);
        } else {
            sumPage=teacherDao.getTeacherSum(key);
            teachers = teacherDao.findall(key,pageCurrent,pageSize);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("getTeachers", JSON.toJSON(teachers));
        jsonObject.put("teacherSumPage",sumPage);
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }


    @Override
    public JSONObject updatepwd(String id, String pwd) {
        if (teacherDao.updatepwd(id, pwd) > 0) {
            return Feedback.info("修改老师密码成功", Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("修改老师密码失败功", Feedback.STATUS_UNKNOWN_ERROR);
    }

    @Override
    public JSONObject delete(String id) {
        if (teacherDao.delete(id)>0)
        {
            return Feedback.info("删除老师成功", Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("删除老师失败",Feedback.STATUS_UNKNOWN_ERROR);
    }

    @Override
    public JSONObject findbyid(String id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("getTeacherbyid", JSON.toJSON(teacherDao.findbyid(id)));
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject createtea(String id, String name, String password, String cellphone) {
        if (teacherDao.findbyid(id) == null) {
            teacherDao.createtea(id, name, password, cellphone);
            return Feedback.info("创建老师成功", Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("创建老师失败", Feedback.STATUS_UNKNOWN_ERROR);
    }
}
