package com.web.blog.service;

import com.web.blog.entity.Skill_map;

import java.util.List;

public interface SkillmapService {
    public List<Skill_map> teacherskill(String tid);
    public List findtea(String name);
}
