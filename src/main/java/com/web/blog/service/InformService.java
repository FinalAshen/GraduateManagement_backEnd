package com.web.blog.service;

import com.web.blog.entity.Inform;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InformService {
    public List<Inform> findall();
    public void createinfo(String title,String content,String creator,String begin_ts,String end_ts,String t_group,String s_group);
}
