package com.fellow.service.cache.impl;

import com.fellow.service.cache.CollegeSchoolCacheService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wubiao on 2016/10/27.
 */
@Service
public class CollegeSchoolCacheServiceImpl implements CollegeSchoolCacheService {

    @Override
    public String getSchoolNameById(Long schoolId) {
        Map<Long,String> schoolMap = new HashMap<Long, String>();
        schoolMap.put(10l,"清华大学");
        schoolMap.put(20l,"北京大学");
        schoolMap.put(30l,"首都理工大学");
        return schoolMap.get(schoolId);
    }
}
