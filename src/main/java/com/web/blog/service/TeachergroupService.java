package com.web.blog.service;

import java.util.List;

public interface TeachergroupService {
    public void creategroup(String group_id,String teacher_id,String direction);
    public List findgroup(String direction);
}
