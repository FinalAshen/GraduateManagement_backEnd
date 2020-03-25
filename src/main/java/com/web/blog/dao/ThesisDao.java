package com.web.blog.dao;
import com.web.blog.entity.Document;
import com.web.blog.entity.Report;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ThesisDao {
    public double score(String sid);
    public int upload_thesis(@Param("designation") String designation,@Param("teacher_id") int teacher_id,
                             @Param("student_id") int student_id,@Param("position") String position);
    public Report getMyThesis(int student_id);
    public int changeMyThesis(@Param("id") int id, @Param("designation") String designation,@Param("position") String position);
}
