package com.web.blog.dao;

import com.web.blog.entity.Document;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

@Repository
public interface DocumentDao {

    public double score(String sid);
    public int upload_document(@Param("designation") String designation,@Param("teacher_id") int teacher_id,
                               @Param("student_id") int student_id,@Param("position") String position);
    public Document getMyDoument(int student_id);
    public int changeMyDocument(@Param("id") int id, @Param("designation") String designation,@Param("position") String position);

}
