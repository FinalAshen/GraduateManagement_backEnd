package com.web.blog.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface ThesisDao {
    public double score(String sid);
}
