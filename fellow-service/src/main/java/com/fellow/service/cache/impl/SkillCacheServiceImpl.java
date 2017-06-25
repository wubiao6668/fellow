package com.fellow.service.cache.impl;

import com.fellow.service.cache.SkillCacheService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wubiao on 2016/10/31.
 */
@Service
public class SkillCacheServiceImpl implements SkillCacheService {

    @Override
    public String getSkillNameById(Long skillId) {
        Map<Long, String> skillMap = new HashMap<Long, String>();
        skillMap.put(10l, "java");
        skillMap.put(20l, "html");
        skillMap.put(30l, "mysql");
        skillMap.put(40l, "spring");
        skillMap.put(50l, "velocty");
        skillMap.put(60l, "c语言");
        skillMap.put(70l, "javascript");
        skillMap.put(80l, "c++");
        return skillMap.get(skillId);
    }
}
