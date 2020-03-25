package com.web.blog.service.imple;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.blog.dao.StudentDao;
import com.web.blog.entity.Student;
import com.web.blog.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;
import com.web.blog.dao.*;
import com.web.blog.entity.*;
import com.web.blog.service.StudentService;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import utils.Feedback;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

@Service
public class StudentServiceImple implements StudentService {


    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private ApplicationDao applicationDao;
    @Autowired
    private DocumentDao documentDao;
    @Autowired
    private ReportDao reportDao;
    @Autowired
    private ThesisDao thesisDao;
    @Autowired
    private InformDao informDao;

    @Override
    public JSONObject findall(String key, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> students;
        if (key == null) {
            students = studentDao.findall("");
        } else {
            students = studentDao.findall(key);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("getStudents", JSON.toJSON(students));
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    @Transactional
    public JSONObject updatepwd(String id, String pwd) {
        if (studentDao.updatepwd(id, pwd) > 0) {
            return Feedback.info("修改学生密码成功", Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("修改学生密码失败", Feedback.STATUS_UNKNOWN_ERROR);
    }

    @Override
    public JSONObject delete(String id) {
        if (studentDao.delete(id) > 0) {
            return Feedback.info("删除学生成功", Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("删除学生失败", Feedback.STATUS_UNKNOWN_ERROR);
    }

    @Override
    public JSONObject findbyid(String id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("getStudentbyid", JSON.toJSON(studentDao.findbyid(id)));
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject updatereply(String id, String flag) {
        if (studentDao.updatereply(id, flag) > 0) {
            return Feedback.info("修改学生答辩状态成功", Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("修改学生答辩状态失败", Feedback.STATUS_UNKNOWN_ERROR);
    }

    @Override
    public JSONObject createstu(String id, String name, String password, String major, String sclass, String cellphone, String teacher_id) {
        if (studentDao.findbyid(id) == null) {
            studentDao.createstu(id, name, password, major, sclass, cellphone, teacher_id);
            return Feedback.info("新增学生成功", Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("新增学生失败", Feedback.STATUS_UNKNOWN_ERROR);
    }

    @Override
    public JSONObject total(String id, BigDecimal total_result) {
        if(studentDao.total(id, total_result)>0)
        {
            return Feedback.info("计算学生总成绩成功", Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("计算学生总成绩失败", Feedback.STATUS_UNKNOWN_ERROR);
    }


    private Boolean checkIdentity(HttpSession session) {
        if (session.getAttribute(StudentService.IDENTITY) == null) {
            return false;
        }
        return true;
    }

    private int getSession(HttpSession session) {
        Student student = (Student) session.getAttribute(StudentService.IDENTITY);
        if (student == null) {
            return 0;
        } else return student.getId();
    }

    @Override
    public JSONObject studentLogin(HttpSession session, Student student) {
        if (studentDao.isExistByID(student.getId()) == 0) {
            return Feedback.info("账号不存在", Feedback.STATUS_ERROR);
        }
        if (studentDao.isFreezeByID(student.getId()) > 0) {
            return Feedback.info("账号已被冻结", Feedback.STATUS_ERROR);
        }
        if (session.getAttribute(StudentService.IDENTITY) != null) {
            session.removeAttribute(StudentService.IDENTITY);
        }
        student = studentDao.studentLogin(student.getId(), student.getPassword());
        if (student != null) {
            student.setPassword(null);
            session.setAttribute(StudentService.IDENTITY, student);
            return Feedback.info("登录成功", Feedback.STATUS_SUCCESS);
        }
        return Feedback.info("登录失败", Feedback.STATUS_UNKNOWN_ERROR);
    }

    @Override
    public JSONObject getInfo(HttpSession session) {
        int id = getSession(session);
        if (id == 0) return Feedback.info("未登陆", Feedback.STATUS_UNKNOWN_ERROR);
        if (!checkIdentity(session)) return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);
        Student student = studentDao.getInfo(id);
        student.setPassword("");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("getInfo", JSON.toJSON(student));
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject changeInfo(HttpSession session, int cellphone) {
        int id = getSession(session);
        if (id == 0) return Feedback.info("未登陆", Feedback.STATUS_UNKNOWN_ERROR);
        if (!checkIdentity(session)) return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);
        int result = studentDao.changeInfo(id, cellphone);
        return Feedback.info("完成", Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject changePassword(HttpSession session, String old_password, String password) {
        int id = getSession(session);
        if (id == 0) return Feedback.info("未登陆", Feedback.STATUS_UNKNOWN_ERROR);
        if (!checkIdentity(session)) return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);
        Student student = studentDao.getInfo(id);
        if (!student.getPassword().equals(old_password)) return Feedback.info("密码错误", Feedback.STATUS_ERROR);
        studentDao.changePassword(id, old_password, password);
        return Feedback.info("完成", Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject getTeacher(int pageSize, int pageCurrent, String skill) {
        PageHelper.startPage(pageCurrent, pageSize);
        List<Teacher> teachers = teacherDao.getTeacher(skill);
        List<Skill_map> skill_maps = teacherDao.getSkill(teachers);
        int j = 0;
        for (int i = 0; i < teachers.size(); i++) {
            List<Skill_map> a = new ArrayList<Skill_map>();
            for (; j < skill_maps.size(); j++) {
                if (teachers.get(i).getId() == skill_maps.get(j).getTeacherid()) {
                    a.add(skill_maps.get(j));
                    if (j == skill_maps.size() - 1) {
                        teachers.get(i).setSkill_maps(a);
                    }
                } else {
                    teachers.get(i).setSkill_maps(a);
                    break;
                }
            }
        }
        PageInfo pageInfo = new PageInfo(teachers);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("getTeacher", JSON.toJSON(pageInfo));
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject applyTeacher(HttpSession session, int teacherid) {
        int id = getSession(session);
        if (id == 0) return Feedback.info("未登陆", Feedback.STATUS_UNKNOWN_ERROR);
        if (!checkIdentity(session)) return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);
        int count = taskDao.if_Elect_Task(id);
        int acount = applicationDao.if_exsist(id);
        System.out.println(count);
        if (count > 0 || acount > 0) {
            return Feedback.info("已经有课题了或已经申请了", Feedback.STATUS_ERROR);
        } else {
            if (applicationDao.applyApplication(1, teacherid) > 0) {
                return Feedback.info("申请成功", Feedback.STATUS_SUCCESS);
            }
        }
        return Feedback.info("申请失败", Feedback.STATUS_ERROR);
    }

    @Override
    public JSONObject check_application(HttpSession session) {
        int id = getSession(session);
        if (id == 0) return Feedback.info("未登陆", Feedback.STATUS_UNKNOWN_ERROR);
        if (!checkIdentity(session)) return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);
        List<Application> applications = applicationDao.check_application(id);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("check_application", JSON.toJSON(applications));
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject accept_application(HttpSession session, int applictaion_id) {
        int id = getSession(session);
        if (id == 0) return Feedback.info("未登陆", Feedback.STATUS_UNKNOWN_ERROR);
        if (!checkIdentity(session)) return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);
        if (applicationDao.accept_application(applictaion_id) == 1) {
            return Feedback.info("同意", Feedback.STATUS_SUCCESS);
        } else return Feedback.info("错误", Feedback.STATUS_UNKNOWN_ERROR);
    }

    @Override
    public JSONObject refuse_application(HttpSession session, int applictaion_id) {
        int id = getSession(session);
        if (id == 0) return Feedback.info("未登陆", Feedback.STATUS_UNKNOWN_ERROR);
        if (!checkIdentity(session)) return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);
        if (applicationDao.refuse_application(applictaion_id) == 1) {
            return Feedback.info("拒绝成功", Feedback.STATUS_SUCCESS);
        } else return Feedback.info("错误", Feedback.STATUS_UNKNOWN_ERROR);
    }

    @Override
    public JSONObject getMyTask(HttpSession session) {
        int id = getSession(session);
        if (id == 0) return Feedback.info("未登陆", Feedback.STATUS_UNKNOWN_ERROR);
        if (!checkIdentity(session)) return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);
        List<Task> tasks = taskDao.getMyTask(id);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("getMyTask", JSON.toJSON(tasks));
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject upload_document(HttpSession session, HttpServletRequest request,
                                      MultipartFile file, int teacher_id) throws IOException {
        int id = getSession(session);
        if (id == 0) return Feedback.info("未登陆", Feedback.STATUS_UNKNOWN_ERROR);
        if (!checkIdentity(session)) return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);

        String filePath = request.getServletContext().getRealPath("/") + "statics/upload/";//地址
        String oldFileName = file.getOriginalFilename();
        String prefix = FilenameUtils.getExtension(oldFileName);//文件后缀名称
        String fileName = System.currentTimeMillis() + Math.random() + "." + prefix;
        String position = (filePath + fileName).replace("\\/", "//");
        int result = -1;
        if (file.isEmpty()) {
            return Feedback.info("文件不存在", Feedback.STATUS_UNKNOWN_ERROR);
        }
        if (file.getSize() > FILE_SIZE) {
            return Feedback.info("上传文件大小不得超过50M", Feedback.STATUS_UNKNOWN_ERROR);
        } else if (prefix.equals("docx") || prefix.equals("doc") || prefix.equals("pdf")) {
            File tarfile = new File(filePath, fileName);
            if (!tarfile.exists()) {
                tarfile.mkdirs();
            }
            file.transferTo(tarfile);
            result = documentDao.upload_document(oldFileName, teacher_id, id, position);
            if (result <= 0) {
                return Feedback.info("文件上传失败", Feedback.STATUS_UNKNOWN_ERROR);
            }
        } else {
            return Feedback.info("文件格式错误", Feedback.STATUS_UNKNOWN_ERROR);
        }
        return Feedback.info("文件上传成功", Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject getMyDocument(HttpSession session, HttpServletRequest request) {
        int id = getSession(session);
        if (id == 0) return Feedback.info("未登陆", Feedback.STATUS_UNKNOWN_ERROR);
        if (!checkIdentity(session)) return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("getMyDocument", JSON.toJSON(documentDao.getMyDoument(id)));
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject changeMyDocument(HttpSession session, HttpServletRequest request, MultipartFile file, int document_id) throws IOException {
        int id = getSession(session);
        if (id == 0) return Feedback.info("未登陆", Feedback.STATUS_UNKNOWN_ERROR);
        if (!checkIdentity(session)) return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);

        File oldfile = new File(documentDao.getMyDoument(id).getPosition());
        if (oldfile.exists()) oldfile.delete();

        String filePath = request.getServletContext().getRealPath("/") + "statics/upload/";//地址
        String oldFileName = file.getOriginalFilename();
        String prefix = FilenameUtils.getExtension(oldFileName);//文件后缀名称
        String fileName = System.currentTimeMillis() + Math.random() + "." + prefix;
        String position = (filePath + fileName).replace("\\/", "//");
        int result = -1;
        if (file.isEmpty()) {
            return Feedback.info("文件不存在", Feedback.STATUS_UNKNOWN_ERROR);
        }
        if (file.getSize() > FILE_SIZE) {
            return Feedback.info("上传文件大小不得超过50M", Feedback.STATUS_UNKNOWN_ERROR);
        } else if (prefix.equals("docx") || prefix.equals("doc") || prefix.equals("pdf")) {
            File tarfile = new File(filePath, fileName);
            if (!tarfile.exists()) {
                tarfile.mkdirs();
            }
            file.transferTo(tarfile);
            result = documentDao.changeMyDocument(document_id, oldFileName, position);
            if (result <= 0) {
                return Feedback.info("文件上传失败", Feedback.STATUS_UNKNOWN_ERROR);
            }
        } else {
            return Feedback.info("文件格式错误", Feedback.STATUS_UNKNOWN_ERROR);
        }
        return Feedback.info("文件上传成功", Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject upload_report(HttpSession session, HttpServletRequest request, MultipartFile file, int teacher_id) throws IOException {
        int id = getSession(session);
        if (id == 0) return Feedback.info("未登陆", Feedback.STATUS_UNKNOWN_ERROR);
        if (!checkIdentity(session)) return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);


        String filePath = request.getServletContext().getRealPath("/") + "statics/upload/";//地址
        String oldFileName = file.getOriginalFilename();
        String prefix = FilenameUtils.getExtension(oldFileName);//文件后缀名称
        String fileName = System.currentTimeMillis() + Math.random() + "." + prefix;
        String position = (filePath + fileName).replace("\\/", "//");
        int result = -1;
        if (file.isEmpty()) {
            return Feedback.info("文件不存在", Feedback.STATUS_UNKNOWN_ERROR);
        }
        if (file.getSize() > FILE_SIZE) {
            return Feedback.info("上传文件大小不得超过50M", Feedback.STATUS_UNKNOWN_ERROR);
        } else if (prefix.equals("docx") || prefix.equals("doc") || prefix.equals("pdf")) {
            File tarfile = new File(filePath, fileName);
            if (!tarfile.exists()) {
                tarfile.mkdirs();
            }
            file.transferTo(tarfile);
            result = reportDao.upload_report(oldFileName, teacher_id, 1, position);
            if (result <= 0) {
                return Feedback.info("文件上传失败", Feedback.STATUS_UNKNOWN_ERROR);
            }
        } else {
            return Feedback.info("文件格式错误", Feedback.STATUS_UNKNOWN_ERROR);
        }
        return Feedback.info("文件上传成功", Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject getMyReport(HttpSession session, HttpServletRequest request) {
        int id = getSession(session);
        if (id == 0) return Feedback.info("未登陆", Feedback.STATUS_UNKNOWN_ERROR);
        if (!checkIdentity(session)) return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("getMyReport", JSON.toJSON(reportDao.getMyReport(id)));
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject changeMyReport(HttpSession session, HttpServletRequest request, MultipartFile file, int report_id) throws IOException {
        int id = getSession(session);
        if (id == 0) return Feedback.info("未登陆", Feedback.STATUS_UNKNOWN_ERROR);
        if (!checkIdentity(session)) return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);

        File oldfile = new File(reportDao.getMyReport(id).getPosition());
        if (oldfile.exists()) oldfile.delete();

        String filePath = request.getServletContext().getRealPath("/") + "statics/upload/";//地址
        String oldFileName = file.getOriginalFilename();
        String prefix = FilenameUtils.getExtension(oldFileName);//文件后缀名称
        String fileName = System.currentTimeMillis() + Math.random() + "." + prefix;
        String position = (filePath + fileName).replace("\\/", "//");
        int result = -1;
        if (file.isEmpty()) {
            return Feedback.info("文件不存在", Feedback.STATUS_UNKNOWN_ERROR);
        }
        if (file.getSize() > FILE_SIZE) {
            return Feedback.info("上传文件大小不得超过50M", Feedback.STATUS_UNKNOWN_ERROR);
        } else if (prefix.equals("docx") || prefix.equals("doc") || prefix.equals("pdf")) {
            File tarfile = new File(filePath, fileName);
            if (!tarfile.exists()) {
                tarfile.mkdirs();
            }
            file.transferTo(tarfile);
            result = reportDao.changeMyReport(report_id, oldFileName, position);
            if (result <= 0) {
                return Feedback.info("文件上传失败", Feedback.STATUS_UNKNOWN_ERROR);
            }
        } else {
            return Feedback.info("文件格式错误", Feedback.STATUS_UNKNOWN_ERROR);
        }
        return Feedback.info("文件上传成功", Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject upload_thesis(HttpSession session, HttpServletRequest request, MultipartFile file, int teacher_id) throws IOException {
        int id = getSession(session);
        if (id == 0) return Feedback.info("未登陆", Feedback.STATUS_UNKNOWN_ERROR);
        if (!checkIdentity(session)) return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);

        String filePath = request.getServletContext().getRealPath("/") + "statics/upload/" + id + "/thesis/";//地址
        String oldFileName = file.getOriginalFilename();
        String prefix = FilenameUtils.getExtension(oldFileName);//文件后缀名称
        String fileName = System.currentTimeMillis() + Math.random() + "." + prefix;
        String position = (filePath + fileName).replace("\\/", "//");
        int result = -1;
        if (file.isEmpty()) {
            return Feedback.info("文件不存在", Feedback.STATUS_UNKNOWN_ERROR);
        }
        if (file.getSize() > FILE_SIZE) {
            return Feedback.info("上传文件大小不得超过50M", Feedback.STATUS_UNKNOWN_ERROR);
        } else if (prefix.equals("docx") || prefix.equals("doc") || prefix.equals("pdf")) {
            File tarfile = new File(filePath, fileName);
            if (!tarfile.exists()) {
                tarfile.mkdirs();
            }
            file.transferTo(tarfile);
            result = thesisDao.upload_thesis(oldFileName, teacher_id, id, position);
            if (result <= 0) {
                return Feedback.info("文件上传失败", Feedback.STATUS_UNKNOWN_ERROR);
            }
        } else {
            return Feedback.info("文件格式错误", Feedback.STATUS_UNKNOWN_ERROR);
        }
        return Feedback.info("文件上传成功", Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject getMyThesis(HttpSession session, HttpServletRequest request) {
        int id = getSession(session);
        if (id == 0) return Feedback.info("未登陆", Feedback.STATUS_UNKNOWN_ERROR);
        if (!checkIdentity(session)) return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("getMyThesis", JSON.toJSON(thesisDao.getMyThesis(id)));
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);

    }

    @Override
    public JSONObject changeMyThesis(HttpSession session, HttpServletRequest request, MultipartFile file, int thesis_id) throws IOException {
        int id = getSession(session);
        if (id == 0) return Feedback.info("未登陆", Feedback.STATUS_UNKNOWN_ERROR);
        if (!checkIdentity(session)) return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);

        File oldfile = new File(thesisDao.getMyThesis(id).getPosition());
        if (oldfile.exists()) oldfile.delete();

        String filePath = request.getServletContext().getRealPath("/") + "statics/upload/" + id + "/thesis/";//地址
        String oldFileName = file.getOriginalFilename();
        String prefix = FilenameUtils.getExtension(oldFileName);//文件后缀名称
        String fileName = System.currentTimeMillis() + Math.random() + "." + prefix;
        String position = (filePath + fileName).replace("\\/", "//");
        int result = -1;
        if (file.isEmpty()) {
            return Feedback.info("文件不存在", Feedback.STATUS_UNKNOWN_ERROR);
        }
        if (file.getSize() > FILE_SIZE) {
            return Feedback.info("上传文件大小不得超过50M", Feedback.STATUS_UNKNOWN_ERROR);
        } else if (prefix.equals("docx") || prefix.equals("doc") || prefix.equals("pdf")) {
            File tarfile = new File(filePath, fileName);
            if (!tarfile.exists()) {
                tarfile.mkdirs();
            }
            file.transferTo(tarfile);
            result = thesisDao.changeMyThesis(thesis_id, oldFileName, position);
            if (result <= 0) {
                return Feedback.info("文件上传失败", Feedback.STATUS_UNKNOWN_ERROR);
            }
        } else {
            return Feedback.info("文件格式错误", Feedback.STATUS_UNKNOWN_ERROR);
        }
        return Feedback.info("文件上传成功", Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject cancelMyApplciation(HttpSession session, int application_id) {
        int id = getSession(session);
        if (id == 0) return Feedback.info("未登陆", Feedback.STATUS_UNKNOWN_ERROR);
        if (!checkIdentity(session)) return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);
        int result = applicationDao.cancelMyApplication(application_id, id);
        if (result == 0) return Feedback.info("取消失败", Feedback.STATUS_UNKNOWN_ERROR);
        return Feedback.info("取消成功", Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject getMyTeacher(HttpSession session) {
        int id = getSession(session);
        if (id == 0) return Feedback.info("未登陆", Feedback.STATUS_UNKNOWN_ERROR);
        if (!checkIdentity(session)) return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);

        List<Teacher> teachers = teacherDao.getMyTeacher(id);
        List<Skill_map> skill_maps = teacherDao.getSkill(teachers);
        int j = 0;
        for (int i = 0; i < teachers.size(); i++) {
            List<Skill_map> a = new ArrayList<Skill_map>();
            for (; j < skill_maps.size(); j++) {
                if (teachers.get(i).getId() == skill_maps.get(j).getTeacherid()) {
                    a.add(skill_maps.get(j));
                    if (j == skill_maps.size() - 1) {
                        teachers.get(i).setSkill_maps(a);
                    }
                } else {
                    teachers.get(i).setSkill_maps(a);
                    break;
                }
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("getMyTeacher", JSON.toJSON(teachers));
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject selectTask(String key) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("selectTask", JSON.toJSON(taskDao.selectTask(key)));
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject checkMyInform(HttpSession session) {
        int id = getSession(session);
        if (id == 0) return Feedback.info("未登陆", Feedback.STATUS_UNKNOWN_ERROR);
        if (!checkIdentity(session)) return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("checkMyInform", JSON.toJSON(informDao.checkMyInform(id)));
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);
    }

    @Override
    public JSONObject choiceTask(HttpSession session, int teacher_id, int task_id) {
        int id = getSession(session);
        if (id == 0) return Feedback.info("未登陆", Feedback.STATUS_UNKNOWN_ERROR);
        System.out.println(id);
        if (!checkIdentity(session)) return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);
        int count = taskDao.if_Elect_Task(id);
        int acount = applicationDao.if_exsist(id);
        System.out.println(count);
        if (count > 0 || acount > 0) {
            return Feedback.info("已经有课题了或已经申请了", Feedback.STATUS_ERROR);
        } else {
            if (applicationDao.choiceTask(id, teacher_id, task_id) > 0) {
                return Feedback.info("申请成功", Feedback.STATUS_SUCCESS);
            }
        }
        return Feedback.info("申请失败", Feedback.STATUS_ERROR);
    }

    @Override
    public JSONObject checkMyResult(HttpSession session) {
        int id = getSession(session);
        if (id == 0) return Feedback.info("未登陆", Feedback.STATUS_UNKNOWN_ERROR);
        if (!checkIdentity(session)) return Feedback.info("权限不足", Feedback.STATUS_ACCESS_FORBID);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("checkMyResult", JSON.toJSON(studentDao.checkMyResult(id)));
        return Feedback.jsonObject(jsonObject, Feedback.STATUS_SUCCESS);

    }

}
