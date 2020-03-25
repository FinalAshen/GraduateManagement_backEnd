package com.web.blog.dao;

import com.web.blog.entity.Application;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationDao {
    public List<Application> findall(String key);
    public void check(@Param("id")String id,@Param("flag")String flag);
}
