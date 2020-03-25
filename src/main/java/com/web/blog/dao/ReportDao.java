package com.web.blog.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface ReportDao {
    public double score(String sid);
}
