package com.web.blog.service.imple;

import com.web.blog.dao.SkillmapDao;
import com.web.blog.entity.Skill_map;
import com.web.blog.service.SkillmapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillmapServiceImple implements SkillmapService {
    private final SkillmapDao skillmapDao;

    @Autowired
    public SkillmapServiceImple(SkillmapDao skillmapDao) {
        this.skillmapDao = skillmapDao;
    }

    @Override
    public List<Skill_map> teacherskill(String tid) {
        return skillmapDao.teacherskill(tid);
    }

    @Override
    public List findtea(String name) {
        return skillmapDao.findtea(name);
    }
}
