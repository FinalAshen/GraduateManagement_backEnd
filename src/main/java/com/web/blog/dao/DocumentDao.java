package com.web.blog.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface DocumentDao {
    public double score(String sid);
}
