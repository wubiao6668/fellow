package com.fellow.service.es.impl;

import com.fellow.domain.es.LostPostEsDomain;
import com.fellow.es.LostPostEsRepository;
import com.fellow.service.es.LostPostEsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by wubiao on 30/9/2017.
 */
@Service
public class LostPostEsServiceImpl implements LostPostEsService {

    @Resource
    private LostPostEsRepository lostPostRepository;

    @Override
    public boolean save(LostPostEsDomain lostPostEsDomain) {
        boolean flag = lostPostRepository.save(lostPostEsDomain);
        return flag;
    }
}
