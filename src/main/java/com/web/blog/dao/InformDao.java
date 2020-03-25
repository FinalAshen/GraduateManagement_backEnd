package com.web.blog.dao;

import com.web.blog.entity.Inform;
import com.web.blog.entity.Skill_map;
import com.web.blog.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformDao {
    public List<Inform> findall();
    public int createinfo(@Param("title")String title,@Param("content")String content,@Param("creator")String creator,@Param("begin_ts") String begin_ts,@Param("end_ts")String end_ts,@Param("t_group")String t_group,@Param("s_group")String s_group);

    List<Inform> checkMyInform(int student_id);
}
