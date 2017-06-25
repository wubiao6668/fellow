package com.fellow.service.cache.impl;

import com.fellow.service.cache.MajorCacheService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wubiao on 2016/10/27.
 */
@Service
public class MajorCacheServiceImpl implements MajorCacheService {

    @Override
    public String getMajorNameById(Long majorId) {
        Map<Long,String> map = new HashMap<Long, String>();
        map.put(10l,"哲学");
        map.put(20l,"哲学");
        map.put(30l,"哲学");
        map.put(40l,"哲学");
        map.put(50l,"哲学");
        return map.get(majorId);
    }
}
