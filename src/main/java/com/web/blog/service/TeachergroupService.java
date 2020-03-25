package com.web.blog.service;

import org.json.JSONObject;

import java.util.List;

public interface TeachergroupService {
    JSONObject creategroup(String group_id,String teacher_id,String direction);
    JSONObject findgroup(String direction);
    JSONObject fenzu(String name, String group_id);
}
