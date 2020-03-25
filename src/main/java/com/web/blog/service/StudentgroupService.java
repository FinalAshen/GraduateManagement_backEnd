package com.web.blog.service;

import org.json.JSONObject;

import java.util.List;

public interface StudentgroupService {
    JSONObject creategroup(String group_id,String student_id,String direction);
    JSONObject findgroup(String direction);
}
