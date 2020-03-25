package com.web.blog.dao;

import com.web.blog.entity.Inform;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformDao {
    public List<Inform> findall();
    public void createinfo(@Param("title")String title,@Param("content")String content,@Param("creator")String creator,@Param("begin_ts") String begin_ts,@Param("end_ts")String end_ts,@Param("t_group")String t_group,@Param("s_group")String s_group);
}
