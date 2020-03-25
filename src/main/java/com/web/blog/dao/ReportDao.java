package com.web.blog.dao;

import com.web.blog.entity.Document;
import com.web.blog.entity.Report;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportDao {

    public double score(String sid);

    public int upload_report(@Param("designation") String designation,@Param("teacher_id") int teacher_id,
                               @Param("student_id") int student_id,@Param("position") String position);
    public Report getMyReport(int student_id);
    public int changeMyReport(@Param("id") int id, @Param("designation") String designation,@Param("position") String position);
}
