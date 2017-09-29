package com.fellow.es.impl;

import com.fellow.es.TestService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by wubiao on 29/9/2017.
 */
@Service
public class TestServiceImpl implements TestService {

    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void test() {
        Order order = new Order();
        order.setId(System.currentTimeMillis());
        order.setSkuName(RandomStringUtils.randomAlphanumeric(6));
        order.setUserName(RandomStringUtils.randomAlphanumeric(4));
        IndexQuery iq = new IndexQueryBuilder().withObject(order).build();
        ArrayList<IndexQuery> inserts = new ArrayList<IndexQuery>();
        inserts.add(iq);
        elasticsearchTemplate.bulkIndex(inserts);
    }

    @PostConstruct
    private void init(){
        if (!elasticsearchTemplate.indexExists(Order.indexName)) {    //注入时调用,当这个索引不存在,则创建索引
            elasticsearchTemplate.createIndex(Order.indexName);
        }
    }
}
