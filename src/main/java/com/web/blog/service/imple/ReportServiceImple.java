package com.web.blog.service.imple;

import com.web.blog.dao.ReportDao;
import com.web.blog.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImple implements ReportService {
    private final ReportDao reportDao;

    @Autowired
    public ReportServiceImple(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @Override
    public double score(String sid) {
        return reportDao.score(sid);
    }
}
