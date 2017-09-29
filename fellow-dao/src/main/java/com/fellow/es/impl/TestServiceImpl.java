package com.fellow.es.impl;

import com.fellow.es.TestService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
        elasticsearchTemplate.createIndex(Order.class,order);
    }
}
