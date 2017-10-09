package com.fellow.service.es.impl;

import com.fellow.domain.constant.EsConstant;
import com.fellow.domain.es.LostPostEsDomain;
import com.fellow.es.LostPostEsRepository;
import com.fellow.service.es.LostPostEsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.highlight.HighlightField;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

/**
 * Created by wubiao on 30/9/2017.
 */
@Service
public class LostPostEsServiceImpl implements LostPostEsService {
    public Log log = LogFactory.getLog(this.getClass());

    @Resource
    private LostPostEsRepository lostPostRepository;
    @Resource
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public LostPostEsDomain save(LostPostEsDomain lostPostEsDomain) {
        lostPostEsDomain = lostPostRepository.save(lostPostEsDomain);
        return lostPostEsDomain;
    }


    @Override
    public Page<LostPostEsDomain> searchByPage(SearchQuery searchQuery) {
        Page<LostPostEsDomain> lostPostEsDomainPage = elasticsearchTemplate.queryForPage(searchQuery, LostPostEsDomain.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                log.info("searchByPage--查询共耗时:" + response.getTookInMillis() + "毫秒.");
                List<LostPostEsDomain> postEsDomainList = new ArrayList<LostPostEsDomain>();
                LostPostEsDomain lostPostEsDomain = null;
                long total = response.getHits().getTotalHits();
                for (SearchHit searchHit : response.getHits()) {
                    if (response.getHits().getHits().length <= 0) {
                        return null;
                    }
                    lostPostEsDomain = new LostPostEsDomain();
                    Map<String, Object> source = searchHit.getSource();
                    lostPostEsDomain.setId((Long) source.get("id"));
                    Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
                    HighlightField highlightNameField = highlightFields.get("postText");
                    if (highlightNameField != null && highlightNameField.fragments() != null) {
                        lostPostEsDomain.setPostText(highlightNameField.fragments()[0].string());
                    } else {
                        lostPostEsDomain.setPostText((String) source.get("postText"));
                    }
                    HighlightField highlightDescField = highlightFields.get("title");
                    if (highlightDescField != null && highlightDescField.fragments() != null) {
                        lostPostEsDomain.setTitle(highlightDescField.fragments()[0].string());
                    } else {
                        lostPostEsDomain.setTitle((String) source.get("title"));
                    }
                    postEsDomainList.add(lostPostEsDomain);
                }
                if (postEsDomainList.size() > 0) {
                    return new AggregatedPageImpl<T>((List<T>) postEsDomainList,pageable,total);
                }
                return null;
            }
        });
        return lostPostEsDomainPage;
    }
}
