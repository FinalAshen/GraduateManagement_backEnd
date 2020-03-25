package com.web.blog.service;

import com.web.blog.entity.Student;

import java.math.BigDecimal;
import java.util.List;

import com.web.blog.entity.Document;
import com.web.blog.entity.Report;
import com.web.blog.entity.Student;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public interface StudentService {
    final String IDENTITY = "stduent";
    final double FILE_SIZE = 51200000;

    JSONObject findall(String key, int pageNum, int pageSize);

    JSONObject updatepwd(String id, String pwd);

    JSONObject delete(String id);

    JSONObject findbyid(String id);

    JSONObject updatereply(String id, String flag);

    JSONObject createstu(String id, String name, String password, String major, String sclass, String cellphone, String teacher_id);

    JSONObject total(String id, BigDecimal total_result);

    JSONObject studentLogin(HttpSession session, Student student);

    JSONObject getInfo(HttpSession session);

    JSONObject changeInfo(HttpSession session, int cellphone);

    JSONObject changePassword(HttpSession session, String old_password, String password);

    JSONObject getTeacher(int pageSize, int pageCurrent, String key);

    JSONObject applyTeacher(HttpSession session, int teacherid);

    JSONObject check_application(HttpSession session);

    JSONObject accept_application(HttpSession session, int applictaion_id);

    JSONObject refuse_application(HttpSession session, int applictaion_id);

    JSONObject cancelMyApplciation(HttpSession session, int id);

    JSONObject getMyTask(HttpSession session);

    JSONObject upload_document(HttpSession session, HttpServletRequest request, MultipartFile file, int teacher_id) throws IOException;

    JSONObject getMyDocument(HttpSession session, HttpServletRequest request);

    JSONObject changeMyDocument(HttpSession session, HttpServletRequest request, MultipartFile file, int document_id) throws IOException;

    JSONObject upload_report(HttpSession session, HttpServletRequest request, MultipartFile file, int teacher_id) throws IOException;

    JSONObject getMyReport(HttpSession session, HttpServletRequest request);

    JSONObject changeMyReport(HttpSession session, HttpServletRequest request, MultipartFile file, int report_id) throws IOException;

    JSONObject upload_thesis(HttpSession session, HttpServletRequest request, MultipartFile file, int teacher_id) throws IOException;

    JSONObject getMyThesis(HttpSession session, HttpServletRequest request);

    JSONObject changeMyThesis(HttpSession session, HttpServletRequest request, MultipartFile file, int thesis_id) throws IOException;

    JSONObject getMyTeacher(HttpSession session);

    JSONObject selectTask(String key);

    JSONObject checkMyInform(HttpSession session);

    JSONObject choiceTask(HttpSession session, int teacher_id, int task_id);

    JSONObject checkMyResult(HttpSession session);
}
