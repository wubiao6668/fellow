package com.fellow.service.es.impl;

import com.fellow.domain.constant.EsConstant;
import com.fellow.service.es.IndexInitEsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by wubiao on 30/9/2017.
 */
@Service
public class IndexInitEsServiceImpl implements IndexInitEsService {
    public Log log = LogFactory.getLog(this.getClass());
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public boolean createIndexIfNotExists() {
        if (null != elasticsearchTemplate) {
            if (!elasticsearchTemplate.indexExists(EsConstant.FELLOW_INDEX_NAME)) {
                elasticsearchTemplate.createIndex(EsConstant.FELLOW_INDEX_NAME);
                log.info("createIndexIfNotExists--成功创建index=" + EsConstant.FELLOW_INDEX_NAME + "");
            } else {
                log.info("createIndexIfNotExists--" + EsConstant.FELLOW_INDEX_NAME + "已存在");
            }
        }
        return true;
    }
}
