package com.web.blog.service.imple;

import com.web.blog.dao.DocumentDao;
import com.web.blog.service.DocumentService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImple implements DocumentService {
    private final DocumentDao documentDao;

    @Autowired
    public DocumentServiceImple(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }

    @Override
    public double score(String sid) {
        return documentDao.score(sid);
    }
}
