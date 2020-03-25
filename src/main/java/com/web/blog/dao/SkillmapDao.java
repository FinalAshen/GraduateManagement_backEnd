package com.web.blog.dao;

import com.web.blog.entity.Skill_map;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillmapDao {
    public List<Skill_map> teacherskill(String tid);
    public List findtea(String name);
}
