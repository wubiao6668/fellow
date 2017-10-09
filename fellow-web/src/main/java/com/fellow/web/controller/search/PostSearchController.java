package com.fellow.web.controller.search;

import com.fellow.domain.constant.EsConstant;
import com.fellow.domain.es.LostPostEsDomain;
import com.fellow.domain.mybatis.PaginatorImpl;
import com.fellow.domain.query.LostPostQuery;
import com.fellow.domain.web.Response;
import com.fellow.service.es.LostPostEsService;
import com.fellow.web.base.WebAbstract;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Created by wubiao on 8/10/2017.
 */
@Controller
@RequestMapping(PostSearchController.VIEW_PATH)
public class PostSearchController extends WebAbstract<LostPostEsService> {
    public static final String VIEW_PATH = "/search/lostPost";

    @RequestMapping("/index")
    public String index(Model model, String keywords, LostPostQuery lostPostQuery) {
        condition(model, keywords, lostPostQuery);
        return "/search/lostPost/index";
    }

    @RequestMapping("/query")
    public String query(Model model, String keywords, LostPostQuery lostPostQuery) {
        Response response = Response.newResponse();
        condition(model, keywords, lostPostQuery);
        return "/search/lostPost/query";
    }

    private void condition(Model model, String keywords, LostPostQuery lostPostQuery) {
        lostPostQuery.setPageSize(3);
        Page<LostPostEsDomain> postEsDomainPage = null;
        if (StringUtils.isNotBlank(keywords)) {
            QueryBuilder queryBuilder = multiMatchQuery(keywords, "title", "postText").analyzer(EsConstant.IK_PINYIN_ANALYZER_NAME);
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            if (null != lostPostQuery.getPostLngStart()
                    && null != lostPostQuery.getPostLngEnd()
                    && null != lostPostQuery.getPostLatStart()
                    && null != lostPostQuery.getPostLatEnd()) {
                nativeSearchQueryBuilder.withFilter(boolQuery().
                        filter(rangeQuery("postLng").gte(lostPostQuery.getPostLngStart()).lte(lostPostQuery.getPostLngEnd())).
                        filter(rangeQuery("postLat").gte(lostPostQuery.getPostLatStart()).lte(lostPostQuery.getPostLatEnd())));
            }
//            nativeSearchQueryBuilder.withSort()
            SearchQuery searchQuery =
                    nativeSearchQueryBuilder.withQuery(queryBuilder).withHighlightFields().
                            withHighlightFields(new HighlightBuilder.Field("*").preTags("<span style='color: #d70c0c;'>").postTags("</span>").requireFieldMatch(false)).
                            withPageable(new PageRequest(lostPostQuery.getPage() - 1, lostPostQuery.getPageSize())).
                            build();
            postEsDomainPage = service.searchByPage(searchQuery);
        }
        PaginatorImpl paginator = null;
        if (null != postEsDomainPage) {
            paginator = new PaginatorImpl(lostPostQuery.getPage(), lostPostQuery.getPageSize(), (int) postEsDomainPage.getTotalElements());
        }
        model.addAttribute("postEsDomainPage", postEsDomainPage);
        model.addAttribute("keywords", StringEscapeUtils.escapeHtml(keywords));
        model.addAttribute("paginator", paginator);
    }


}
