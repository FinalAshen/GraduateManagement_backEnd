package com.web.blog.service;

import java.util.List;

public interface TaskService {
    public void check(String id,String flag,String review);
    public List findstu(String teacher_id,String direction);
}
