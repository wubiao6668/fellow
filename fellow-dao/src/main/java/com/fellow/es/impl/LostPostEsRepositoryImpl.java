package com.fellow.es.impl;

import com.fellow.domain.es.LostPostEsDomain;
import com.fellow.es.LostPostEsRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Created by wubiao on 30/9/2017.
 */
@Repository
public class LostPostEsRepositoryImpl implements LostPostEsRepository {
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public boolean save(LostPostEsDomain lostPostEsDomain) {
        IndexQuery iq = new IndexQueryBuilder().withObject(lostPostEsDomain).build();
        ArrayList<IndexQuery> inserts = new ArrayList<IndexQuery>();
        inserts.add(iq);
        elasticsearchTemplate.bulkIndex(inserts);
        return true;
    }

    public void queryB() {
        QueryBuilder queryBuilder = queryStringQuery("秋裤");
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
        Page<LostPostEsDomain> lostPostEsDomainPage = elasticsearchTemplate.queryForPage(searchQuery, LostPostEsDomain.class);
        System.out.print(lostPostEsDomainPage);
    }
}










