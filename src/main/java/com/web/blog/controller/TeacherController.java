package com.web.blog.controller;

import com.web.blog.entity.TaskInfo;
import com.web.blog.entity.TeacherInfo;
import com.web.blog.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import utils.Feedback;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(value = "/api/teacher", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String teacherLogin(HttpSession session, TeacherInfo teacherInfo) {
        return teacherService.login(session, teacherInfo).toString();
    }

    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    @ResponseBody
    public String teacherGetInfo(HttpSession session) {
        return teacherService.getInfo(session).toString();
    }

    @RequestMapping(value = "/create/task", method = RequestMethod.POST)
    @ResponseBody
    public String createTask(HttpSession session, TaskInfo taskInfo) {
        return teacherService.createTask(session, taskInfo).toString();
    }

    @RequestMapping(value = "/delete/task", method = RequestMethod.GET)
    @ResponseBody
    public String deleteTask(HttpSession session, Integer id) {
        return teacherService.deleteTask(session, id).toString();
    }

    @RequestMapping(value = "/update/task", method = RequestMethod.POST)
    @ResponseBody
    public String updateTask(HttpSession session, TaskInfo taskInfo) {
        System.out.println(taskInfo.getDesignation() + " " + taskInfo.getDirection() + " " + taskInfo.getCycle() + " "+ taskInfo.getId());
        return teacherService.updateTask(session, taskInfo).toString();
    }

    @RequestMapping(value = "/select/task", method = RequestMethod.GET)
    @ResponseBody
    public String selectTask(Integer page, Integer pageSize) {
        return teacherService.selectTask((page - 1) * pageSize, pageSize).toString();
    }

    @RequestMapping(value = "/select/student", method = RequestMethod.GET)
    @ResponseBody
    public String selectStudent(HttpSession session, Integer page, Integer pageSize) {
        return teacherService.selectStudent((page - 1) * 10, pageSize).toString();
    }

    @RequestMapping(value = "/select/student/available", method = RequestMethod.GET)
    @ResponseBody
    public String selectStudentAvailable(HttpSession session, TaskInfo taskInfo) {
        return teacherService.selectStudentAvailable(session, taskInfo).toString();
    }

    @RequestMapping(value = "/select/student/cancel", method = RequestMethod.GET)
    @ResponseBody
    public String selectStudentCancel(HttpSession session, TaskInfo taskInfo) {
        return teacherService.selectStudentCancel(session, taskInfo).toString();
    }

    @RequestMapping(value = "/given/charter/create", method = RequestMethod.POST)
    @ResponseBody
    public String givenCharterCreate(HttpSession session, MultipartFile[] attachs, Integer taskID, HttpServletRequest request) {
        return teacherService.givenCharterCreate(session, attachs, taskID, request).toString();
    }

    @RequestMapping(value = "/given/charter/update", method = RequestMethod.POST)
    @ResponseBody
    public String givenCharterUpdate(HttpSession session, MultipartFile[] attachs, TaskInfo taskInfo, HttpServletRequest request) {
        return teacherService.givenCharterUpdate(session, attachs, taskInfo, request).toString();
    }

    @RequestMapping(value = "/given/charter/delete", method = RequestMethod.GET)
    @ResponseBody
    public String givenCharterDelete(HttpSession session, Integer taskID, String fileName, HttpServletRequest request) {
        if (teacherService.givenCharterDelete(session, taskID, fileName, request)) {
            return Feedback.info("移除成功", Feedback.STATUS_SUCCESS).toString();
        }
        return Feedback.info("移除失败", Feedback.STATUS_ERROR).toString();
    }

    @RequestMapping(value = "/show/group/teacher", method = RequestMethod.GET)
    @ResponseBody
    public String showGroupTeacher(Integer teacherID, Integer page, Integer pageSize, Integer flag) {
        return teacherService.showGroupTeacher(teacherID, (page - 1) * pageSize, pageSize, flag).toString();
    }

    @RequestMapping(value = "/show/group/student", method = RequestMethod.GET)
    @ResponseBody
    public String showGroupStudent(Integer studentID, Integer page, Integer pageSize, Integer flag) {
        return teacherService.showGroupStudent(studentID, (page - 1) * pageSize, pageSize, flag).toString();
    }

    @RequestMapping(value = "/select/conventStudent", method = RequestMethod.GET)
    @ResponseBody
    public String selectConventStudent(HttpSession session, Integer teacherID, Integer page, Integer pageSize, Integer status) {
        return teacherService.selectConventStudent(session, teacherID, (page - 1) * pageSize, pageSize, status).toString();
    }

    @RequestMapping(value = "/convent/student", method = RequestMethod.GET)
    @ResponseBody
    public String conventStudent(HttpSession session, Integer studentID, Integer teacherID) {
        return teacherService.conventStudent(session, studentID, teacherID).toString();
    }

    @RequestMapping(value = "/define/taskForStudent", method = RequestMethod.POST)
    @ResponseBody
    public String defineTaskForStudent(HttpSession session, TaskInfo taskInfo) {
        System.out.println(taskInfo.getDesignation() + " " + taskInfo.getStudent_id());
        return teacherService.defineTaskForStudent(session, taskInfo).toString();
    }

    @RequestMapping(value = "/getMyStudent", method = RequestMethod.GET)
    @ResponseBody
    public String getMyStudent(HttpSession session, Integer page, Integer pageSize) {
        return teacherService.getMyStudent(session, (page - 1) * pageSize, pageSize).toString();
    }

    @RequestMapping(value = "/getApplyMyStudent", method = RequestMethod.GET)
    @ResponseBody
    public String getApplyMyStudent(HttpSession session, Integer page, Integer pageSize) {
        return teacherService.getApplyMyStudent(session, (page - 1) * pageSize, pageSize).toString();
    }

    @RequestMapping(value = "/agreeOwnStudent", method = RequestMethod.GET)
    @ResponseBody
    public String agreeOwnStudent(HttpSession session, Integer studentID) {
        return teacherService.agreeOwnStudent(session, studentID).toString();
    }

    @RequestMapping(value = "/getInviteStudent", method = RequestMethod.GET)
    @ResponseBody
    public String getInviteStudent(HttpSession session, Integer page, Integer pageSize) {
        return teacherService.getInviteStudent(session, (page - 1) * pageSize, pageSize).toString();
    }

    @RequestMapping(value = "/getHasAcceptStudent", method = RequestMethod.GET)
    @ResponseBody
    public String getHasAcceptStudent(HttpSession session, Integer page, Integer pageSize) {
        return teacherService.getHasAcceptStudent(session, (page - 1) * pageSize, pageSize).toString();
    }

}
