package com.web.blog.controller;

import com.alibaba.fastjson.JSON;
import com.web.blog.entity.Document;
import com.web.blog.entity.Student;
import com.web.blog.service.StudentService;
import com.web.blog.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/student", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
@CrossOrigin(origins = "*", maxAge = 3600)
public class StudentControler {
    private final StudentService studentService;

    @Autowired
    public StudentControler(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/studentLogin")
    @ResponseBody
    public String studentLogin(HttpSession session,int id,String password){
        Student student=new Student();
        student.setId(id);
        student.setPassword(password);
        return studentService.studentLogin(session,student).toString();
    }
    @RequestMapping(value = "/getTeacher")
    @ResponseBody
    public String getTeacher(int pageSize, int pageCurrent,String skill){
        return studentService.getTeacher(pageSize,pageCurrent,skill).toString();
    }

    @RequestMapping(value = "/getInfo")
    @ResponseBody
    public String getInfo(HttpSession session){
        return studentService.getInfo(session).toString();
    }

    @RequestMapping(value = "/changeInfo")
    @ResponseBody
    public String changeInfo(HttpSession session,int cellphone){
        return studentService.changeInfo(session,cellphone).toString();
    }

    @RequestMapping(value = "/changePassword")
    @ResponseBody
    public String changePassword(HttpSession session,String old_password,String password){
        return studentService.changePassword(session,old_password,password).toString();
    }

    @RequestMapping(value = "/applyTeacher")
    @ResponseBody
    public String applyTeacher(HttpSession session,int teacherid){
        return studentService.applyTeacher(session,teacherid).toString();
    }

    @RequestMapping(value = "/check_application")
    @ResponseBody
    public String check_application(HttpSession session){
        return studentService.check_application(session).toString();
    }
    @RequestMapping(value = "/accept_application")
    @ResponseBody
    public String accept_application(HttpSession session,int application_id){
        return studentService.accept_application(session,application_id).toString();
    }

    @RequestMapping(value = "/refuse_application")
    @ResponseBody
    public String refuse_application(HttpSession session,int application_id){
        return studentService.refuse_application(session,application_id).toString();
    }

    @RequestMapping(value = "getMyTask")
    @ResponseBody
    public String getMyTask(HttpSession session){
        return studentService.getMyTask(session).toString();
    }

    @RequestMapping(value = "/upload_document")
    @ResponseBody
    public String upload_document(HttpSession session, HttpServletRequest request,
                                  @RequestParam MultipartFile file,@RequestParam int teacher_id) throws IOException {
        return studentService.upload_document(session,request,file,teacher_id).toString();
    }

    @RequestMapping("/getMyDocument")
    @ResponseBody
    public String getMyDoument(HttpSession session,HttpServletRequest request){
        return studentService.getMyDocument(session,request).toString();
    }

    @RequestMapping("/changeMyDocument")
    @ResponseBody
    public String changeMyDocument(HttpSession session,HttpServletRequest request,
                                   @RequestParam MultipartFile file,@RequestParam int document_id)throws IOException{
        return studentService.changeMyDocument(session,request,file,document_id).toString();
    }

    @RequestMapping(value = "/upload_report")
    @ResponseBody
    public String upload_report(HttpSession session, HttpServletRequest request,
                                  @RequestParam MultipartFile file,@RequestParam int teacher_id) throws IOException {
        return studentService.upload_report(session,request,file,teacher_id).toString();
    }

    @RequestMapping("/getMyReport")
    @ResponseBody
    public String getMyReport(HttpSession session,HttpServletRequest request){
        return studentService.getMyReport(session,request).toString();
    }

    @RequestMapping("/changeMyReport")
    @ResponseBody
    public String changeMyReport(HttpSession session,HttpServletRequest request,
                                   @RequestParam MultipartFile file,@RequestParam int report_id)throws IOException{
        return studentService.changeMyReport(session,request,file,report_id).toString();
    }

    @RequestMapping(value = "/upload_thesis")
    @ResponseBody
    public String upload_thesis(HttpSession session, HttpServletRequest request,
                                @RequestParam MultipartFile file,@RequestParam int teacher_id) throws IOException {
        return studentService.upload_thesis(session,request,file,teacher_id).toString();
    }

    @RequestMapping("/getMyThesis")
    @ResponseBody
    public String getMyThesis(HttpSession session,HttpServletRequest request){
        return studentService.getMyThesis(session,request).toString();
    }

    @RequestMapping("/changeMyThesis")
    @ResponseBody
    public String changeMyThesis(HttpSession session,HttpServletRequest request,
                                   @RequestParam MultipartFile file,@RequestParam int thesis_id)throws IOException{
        return studentService.changeMyThesis(session,request,file,thesis_id).toString();
    }

    @RequestMapping("/cancelMyApplication")
    @ResponseBody
    public String cancelMyApplication(HttpSession session,int id){
        return studentService.cancelMyApplciation(session,id).toString();
    }

    @RequestMapping("/getMyTeacher")
    @ResponseBody
    public String getMyTeacher(HttpSession session){
        return studentService.getMyTeacher(session).toString();
    }

    @RequestMapping("/selectTask")
    @ResponseBody
    public String selectTask(String key){
        return studentService.selectTask(key).toString();
    }

    @RequestMapping("/checkMyInform")
    @ResponseBody
    public String checkMyInform(HttpSession session){
        return studentService.checkMyInform(session).toString();
    }

    @RequestMapping("/choiceTask")
    @ResponseBody
    public String choiceTask(HttpSession session,int teacher_id,int task_id){
        return studentService.choiceTask(session,teacher_id,task_id).toString();
    }

    @RequestMapping("/checkMyResult")
    @ResponseBody
    public String checkMyResult(HttpSession session){
        return studentService.checkMyResult(session).toString();
    }
}
