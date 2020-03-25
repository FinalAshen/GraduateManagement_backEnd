package com.web.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachergroupDao {
    public void creategroup(@Param("group_id")String group_id,@Param("teacher_id")String teacher_id,@Param("direction")String direction);
    public List findgroup(@Param("direction")String direction);
}
