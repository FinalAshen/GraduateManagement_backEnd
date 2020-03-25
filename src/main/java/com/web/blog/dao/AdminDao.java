package com.web.blog.dao;

import com.web.blog.entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao {
    Admin getInfoById(String id);
}
