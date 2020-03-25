package com.web.blog.controller;

import com.alibaba.fastjson.JSONObject;
import com.web.blog.entity.*;
import com.web.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping(value = "/admin", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdmController {

    private final StudentService studentService;
    private final TeacherService teacherService;
    private final ApplicationService applicationService;
    private final SkillmapService skillmapService;
    private final InformService informService;
    private final TaskService taskService;
    private final DocumentService documentService;
    private final ReportService reportService;
    private final ThesisService thesisService;
    private final StudentgroupService studentgroupService;
    private final TeachergroupService teachergroupService;
    private final AdminService adminService;

    @Autowired
    public AdmController(StudentService studentService, TeacherService teacherService, ApplicationService applicationService, SkillmapService skillmapService, InformService informService, TaskService taskService, DocumentService documentService, ReportService reportService, ThesisService thesisService, StudentgroupService studentgroupService, TeachergroupService teachergroupService, AdminService adminService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.applicationService = applicationService;
        this.skillmapService = skillmapService;
        this.informService = informService;
        this.taskService = taskService;
        this.documentService = documentService;
        this.reportService = reportService;
        this.thesisService = thesisService;
        this.studentgroupService = studentgroupService;
        this.teachergroupService = teachergroupService;
        this.adminService = adminService;
    }

    @RequestMapping(value = "/admlogin", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String login(HttpSession session, String id, String password) {
        System.out.println(id+password);
        return adminService.login(session,id,password).toString();
    }

    @RequestMapping(value = "/admlogout", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String logout(HttpSession session) {
        return adminService.logout(session).toString();
    }

    @RequestMapping(value = "/stuaccount", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String students(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize, String key) throws Exception {
        List<Student> students = studentService.findall(key, pageNum, pageSize);
        System.out.println(JSONObject.toJSONString(students));
        return JSONObject.toJSONString(students);
    }

    //学生操作
    @RequestMapping(value = "/updatestudentpwd", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public void updatestudentpwd(String id, String pwd) {
        studentService.updatepwd(id, pwd);
    }

    @RequestMapping(value = "/stuinfobyid", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findstubyid(String id) {
        Student student = studentService.findbyid(id);
        return JSONObject.toJSONString(student);
    }

    @RequestMapping(value = "/updatereply", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public void updatestureply(String id, String flag) {
        studentService.updatereply(id, flag);
    }

    @RequestMapping(value = "/deletestu", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public void deletestu(String id) {
        studentService.delete(id);
    }

    //老师操作
    @RequestMapping(value = "/tchaccount", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String teachers(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize, String key) throws Exception {
        List<Teacher> teachers = teacherService.findall(key, pageNum, pageSize);
        System.out.println(JSONObject.toJSONString(teachers));
        return JSONObject.toJSONString(teachers);
    }

    @RequestMapping(value = "/updateteacherpwd", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public void updateteacher(String id, String pwd) {

        teacherService.updatepwd(id, pwd);
        System.out.println(id + pwd);
    }

    @RequestMapping(value = "/teainfobyid", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findteabyid(String id) {
        Teacher teacher = teacherService.findbyid(id);
        return JSONObject.toJSONString(teacher);
    }

    @RequestMapping(value = "/deletetea", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public void deletetea(String id) {
        teacherService.delete(id);
    }

    //申请操作
    @RequestMapping(value = "/applicaccount", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findallapplication(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize, String key) {
        List<Application> applications = applicationService.findall(key, pageNum, pageSize);
        return JSONObject.toJSONString(applications);
    }

    @RequestMapping(value = "/checkapplication", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public void checkapplication(String id, String flag) {
        applicationService.check(id, flag);
    }

    @RequestMapping(value = "/teacherskill", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String teacherskill(String tid) {
        List<Skill_map> teacherskill = skillmapService.teacherskill(tid);
        return JSONObject.toJSONString(teacherskill);
    }

    @RequestMapping(value = "/findallinform", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findallinform() {
        return JSONObject.toJSONString(informService.findall());
    }

    @RequestMapping(value = "/checktask", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public void checktask(String id, String flag, String review) {
        taskService.check(id, flag, review);
    }

    @RequestMapping(value = "/createstu", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public void createstu(String id, String name, String password, String major, String sclass, String cellphone, String teacher_id) {
        System.out.println(studentService.createstu(id, name, password, major, sclass, cellphone, teacher_id));
    }

    @RequestMapping(value = "/createinfo", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public void createinfo(String title, String content, String creator, String begin_ts, String end_ts, String t_group, String s_group) {
        informService.createinfo(title, content, creator, begin_ts, end_ts, t_group, s_group);
    }

    @RequestMapping(value = "/createtea", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public void createtea(String id, String name, String password, String cellphone) {
        teacherService.createtea(id, name, password, cellphone);
    }

    @RequestMapping(value = "/totalresult", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public void calculateresult(String id) {
        double score = documentService.score(id) * 0.2 + reportService.score(id) * 0.2 + thesisService.score(id) * 0.6;
        BigDecimal bigDecimal = new BigDecimal(score);
        studentService.total(id, bigDecimal);
    }

    @RequestMapping(value = "/fenzu", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public void fenzu(String name, String group_id) {
        List t = skillmapService.findtea(name);
        for (int i = 0; i < t.size(); i++) {
            List s = taskService.findstu(t.get(i).toString(), name);
            for (int j = 0; j < s.size(); j++) {
                studentgroupService.creategroup(group_id, s.get(i).toString(), name);
            }
            teachergroupService.creategroup(group_id, t.get(i).toString(), name);
        }
    }

    @RequestMapping(value = "/findstugroupbydirectiton", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findstugroupbydirectiton(String direction) {
        return JSONObject.toJSONString(studentgroupService.findgroup(direction));
    }

    @RequestMapping(value = "/findteagroupbydirectiton", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findteagroupbydirectiton(String direction) {
        return JSONObject.toJSONString(teachergroupService.findgroup(direction));
    }

}
