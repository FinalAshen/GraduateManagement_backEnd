package com.web.blog.service;

import com.web.blog.entity.Application;
import org.json.JSONObject;

import java.util.List;

public interface ApplicationService {
    JSONObject findall(int pageSize,int pageCurrent,String key);
    JSONObject check(String id,String flag);
}
