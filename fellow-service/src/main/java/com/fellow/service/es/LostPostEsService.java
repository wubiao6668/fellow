package com.fellow.service.es;

import com.fellow.domain.es.LostPostEsDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

/**
 * Created by wubiao on 30/9/2017.
 */
public interface LostPostEsService {

    LostPostEsDomain save(LostPostEsDomain lostPostEsDomain);

    Page<LostPostEsDomain> searchByPage(SearchQuery searchQuery);

}
