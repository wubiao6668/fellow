package com.fellow.es.impl;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by wubiao on 29/9/2017.
 */
@Document(indexName = "test_es_order_index", type = "test_es_order_type")
public class Order {
    public static final String indexName = "test_es_order_index";
    @Id
    private Long id;
    private String userName;
    private String skuName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", skuName='" + skuName + '\'' +
                '}';
    }
}
