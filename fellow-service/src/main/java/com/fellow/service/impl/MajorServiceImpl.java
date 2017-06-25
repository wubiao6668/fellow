/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2017
 */

package com.fellow.service.impl;

import com.fellow.dao.MajorMapper;
import com.fellow.domain.model.Major;
import com.fellow.domain.query.MajorQuery;
import com.fellow.service.MajorService;
import com.fellow.service.base.ServiceAbstract;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MajorServiceImpl extends ServiceAbstract<MajorMapper> implements MajorService {

    @Transactional(readOnly = true)
    @Override
    public List<Major> selectMajorByUpid(MajorQuery majorQuery) {
        return repository.selectMajorByUpid(majorQuery);
    }

    @Transactional
    @Override
    public void initMajor(Map<String, String> marjorMap) {
        Major insertMajor = null;
        for (String key : marjorMap.keySet()) {
            insertMajor = new Major();
            insertMajor.setName(key);
            insertMajor.setLevel(0);
            insertMajor.setUpid(0l);
            insertMajor.setCreateAccount("system");
            insertMajor.setCreateName("system");
            insertMajor.setCreateTime(new Date());
            repository.insertSelective(insertMajor);
            Long uid = insertMajor.getId();
            String[] values = marjorMap.get(key).split("[0-9]{1,} ");
            System.out.println("**************       " + key + "      ************");
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                if (StringUtils.isBlank(value)){
                    continue;
                }
                insertMajor = new Major();
                insertMajor.setName(value);
                insertMajor.setLevel(1);
                insertMajor.setUpid(uid);
                insertMajor.setCreateAccount("system");
                insertMajor.setCreateName("system");
                insertMajor.setCreateTime(new Date());
                repository.insertSelective(insertMajor);
            }
        }
    }
}
