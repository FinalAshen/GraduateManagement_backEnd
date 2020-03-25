package com.web.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDao {
    public void check(@Param("id")String id,@Param("flag")String flag,@Param("review") String review);
    public List findstu(@Param("teacher_id") String teacher_id,@Param("direction")String direction);
}
