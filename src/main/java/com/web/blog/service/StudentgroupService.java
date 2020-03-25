package com.web.blog.service;

import java.util.List;

public interface StudentgroupService {
    public void creategroup(String group_id,String student_id,String direction);
    public List findgroup(String direction);
}
