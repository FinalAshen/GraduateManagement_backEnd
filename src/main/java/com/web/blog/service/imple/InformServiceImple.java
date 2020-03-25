package com.web.blog.service.imple;

import com.web.blog.dao.InformDao;
import com.web.blog.entity.Inform;
import com.web.blog.service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformServiceImple implements InformService {
    private final InformDao informDao;

    @Autowired
    public InformServiceImple(InformDao informDao) {
        this.informDao = informDao;
    }

    @Override
    public List<Inform> findall() {
        return informDao.findall();
    }

    @Override
    public void createinfo(String title, String content, String creator, String begin_ts, String end_ts, String t_group, String s_group) {
        System.out.println(title+content+creator+begin_ts+end_ts+t_group+s_group);
        informDao.createinfo(title,content,creator,begin_ts,end_ts,t_group,s_group);
    }
}
