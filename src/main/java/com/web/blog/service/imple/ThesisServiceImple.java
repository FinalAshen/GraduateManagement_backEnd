package com.web.blog.service.imple;

import com.web.blog.dao.ThesisDao;
import com.web.blog.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThesisServiceImple implements ThesisService {
    private final ThesisDao thesisDao;

    @Autowired
    public ThesisServiceImple(ThesisDao thesisDao) {
        this.thesisDao = thesisDao;
    }

    @Override
    public double score(String sid) {
        return thesisDao.score(sid);
    }
}
