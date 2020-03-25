package com.web.blog.service;

import com.web.blog.entity.Application;

import java.util.List;

public interface ApplicationService {
    public List<Application> findall(String key,int pageNum, int pageSize);
    public void check(String id,String flag);
}
