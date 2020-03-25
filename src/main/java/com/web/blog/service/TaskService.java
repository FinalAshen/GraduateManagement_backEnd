package com.web.blog.service;

import org.json.JSONObject;

import java.util.List;

public interface TaskService {
    JSONObject check(String id, String flag, String review);
    public List findstu(String teacher_id,String direction);
}
