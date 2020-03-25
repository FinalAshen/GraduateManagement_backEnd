package com.web.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentgroupDao {
    public void creategroup(@Param("group_id") String group_id,@Param("student_id") String student_id,@Param("direction")String direction);
    public List findgroup(@Param("direction")String direction);
}
