package com.fellow.service.cache.impl;

import com.fellow.service.cache.CityCacheService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by wubiao on 2016/10/26.
 */
@Service
public class CityCacheServiceImpl implements CityCacheService {

    @Override
    public String getNameById(Long cityId) {
        HashMap<Long, String> firstCity = new HashMap<Long, String>();
        firstCity.put(10l,"辽宁");
        firstCity.put(20l,"海南");
        firstCity.put(30l,"沈阳");
        firstCity.put(40l,"海口");
        return firstCity.get(cityId);
    }
}
