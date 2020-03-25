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
        System.out.println(id + password);
        return adminService.login(session, id, password).toString();
    }

    @RequestMapping(value = "/admlogout", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String logout(HttpSession session) {
        return adminService.logout(session).toString();
    }

    @RequestMapping(value = "/stuaccount", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String students(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize, String key) throws Exception {
        return studentService.findall(key, pageNum, pageSize).toString();
    }

    //学生操作
    @RequestMapping(value = "/updatestudentpwd", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String updatestudentpwd(String id, String pwd) {
        return studentService.updatepwd(id, pwd).toString();
    }

    @RequestMapping(value = "/stuinfobyid", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findstubyid(String id) {
        return studentService.findbyid(id).toString();
    }

    @RequestMapping(value = "/updatestureply", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String updatestureply(String id, String flag) {
        return studentService.updatereply(id, flag).toString();
    }

    @RequestMapping(value = "/createstu", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String createstu(String id, String name, String password, String major, String sclass, String cellphone, String teacher_id) {
        return studentService.createstu(id, name, password, major, sclass, cellphone, teacher_id).toString();
    }

    @RequestMapping(value = "/deletestu", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String deletestu(String id) {
        return studentService.delete(id).toString();
    }

    @RequestMapping(value = "/totalresult", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String calculateresult(String id) {
        double score = documentService.score(id) * 0.2 + reportService.score(id) * 0.2 + thesisService.score(id) * 0.6;
        BigDecimal bigDecimal = new BigDecimal(score);
        return studentService.total(id, bigDecimal).toString();
    }

    //老师操作
    @RequestMapping(value = "/tchaccount", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String teachers(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize, String key) throws Exception {
        return teacherService.findall(key, pageNum, pageSize).toString();
    }

    @RequestMapping(value = "/updateteacherpwd", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String updateteacher(String id, String pwd) {
        return teacherService.updatepwd(id, pwd).toString();
    }

    @RequestMapping(value = "/teainfobyid", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findteabyid(String id) {
        return teacherService.findbyid(id).toString();
    }

    @RequestMapping(value = "/deletetea", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String deletetea(String id) {
        return teacherService.delete(id).toString();
    }

    @RequestMapping(value = "/createtea", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String createtea(String id, String name, String password, String cellphone) {
        return teacherService.createtea(id, name, password, cellphone).toString();
    }

    //申请操作
    @RequestMapping(value = "/applicaccount", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findallapplication(@RequestParam("page") Integer pageNum, @RequestParam("limit") Integer pageSize, String key) {
        return applicationService.findall(key, pageNum, pageSize).toString();
    }

    @RequestMapping(value = "/checkapplication", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String checkapplication(String id, String flag) {
        return applicationService.check(id, flag).toString();
    }

    //    @RequestMapping(value = "/teacherskill", produces = "text/plain;charset=utf-8")
//    @ResponseBody
//    public String teacherskill(String tid) {
//        List<Skill_map> teacherskill = skillmapService.teacherskill(tid);
//        return JSONObject.toJSONString(teacherskill);
//    }
//通知操作
    @RequestMapping(value = "/findallinform", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findallinform() {
        return informService.findall().toString();
    }

    @RequestMapping(value = "/createinfo", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String createinfo(String title, String content, String creator, String begin_ts, String end_ts, String t_group, String s_group) {
        return informService.createinfo(title, content, creator, begin_ts, end_ts, t_group, s_group).toString();
    }

    //课题申请审核
    @RequestMapping(value = "/checktask", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String checktask(String id, String flag, String review) {
        return taskService.check(id, flag, review).toString();
    }


    @RequestMapping(value = "/fenzu", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String fenzu(String name, String group_id) {
        return teachergroupService.fenzu(name, group_id).toString();
    }

    @RequestMapping(value = "/findstugroupbydirectiton", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findstugroupbydirectiton(String direction) {
        return studentgroupService.findgroup(direction).toString();
    }

    @RequestMapping(value = "/findteagroupbydirectiton", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findteagroupbydirectiton(String direction) {
        return teachergroupService.findgroup(direction).toString();
    }

}
