package com.fellow.web.controller.myCenter;

import com.fellow.common.util.VelocityUtil;
import com.fellow.domain.enums.PostEnum;
import com.fellow.domain.model.LostPost;
import com.fellow.domain.model.Post;
import com.fellow.domain.query.LostPostQuery;
import com.fellow.domain.query.PostQuery;
import com.fellow.domain.web.Response;
import com.fellow.service.LostPostService;
import com.fellow.service.PostService;
import com.fellow.web.base.WebAbstract;
import org.apache.commons.collections.CollectionUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wubiao on 2016/11/21.
 */

@Controller
@RequestMapping(PostMangerController.VIEW_PATH)
public class PostMangerController extends WebAbstract<LostPostService> {
    public static final String VIEW_PATH = "/myCenter/postManger";

    @RequestMapping("/index")
    public String index(Model model) {
        LostPostQuery postQuery = new LostPostQuery();
        postQuery.setPage(1);
        postQuery.setPageSize(10);
        postQuery.initMysqlLimit();
        postQuery.setPostAccount(super.getAccount());
        List<LostPost> postList = service.selectMyPost(postQuery);
        boolean isHadPrePage = 1 >= postQuery.getPage() ? false : true;
        boolean isHadNextPage = false;
        if (CollectionUtils.isNotEmpty(postList)) {
//            for (LostPost postTemp : postList) {
//                postTemp.setPostTypeString(PostEnum.getValueByKey(postTemp.getPostType()));
//            }
            isHadNextPage = postList.size() < postQuery.getPageSize() ? false : true;
        }
        model.addAttribute("isHadPrePage", isHadPrePage);
        model.addAttribute("isHadNextPage", isHadNextPage);
        model.addAttribute("prePage", postQuery.getPage() - 1);
        model.addAttribute("nextPage", postQuery.getPage() + 1);
        model.addAttribute("postList", postList);
        model.addAttribute("postTypeEnums", PostEnum.values());
        return VIEW_PATH + "/index";
    }

    @RequestMapping(value = "/queryMyPost")
    @ResponseBody
    public Response queryMyPost(LostPostQuery postQuery) {
        Response response = Response.newResponse();
        postQuery.setPageSize(10);
        postQuery.initMysqlLimit();
        postQuery.setPostAccount(super.getAccount());
        List<LostPost> postList = service.selectMyPost(postQuery);
        boolean isHadPrePage = 1 >= postQuery.getPage() ? false : true;
        boolean isHadNextPage = false;
        if (CollectionUtils.isNotEmpty(postList)) {
//            for (Post postTemp : postList) {
//                postTemp.setPostTypeString(PostEnum.getValueByKey(postTemp.getPostType()));
//            }
            isHadNextPage = postList.size() < postQuery.getPageSize() ? false : true;
        } else {
            if (1 < postQuery.getPage()) {
                response.setMessage("已经是最后一页了!");
                return response;
            }
        }
        Context context = new VelocityContext();
        context.put("collectionUtils", new CollectionUtils());
        context.put("isHadPrePage", isHadPrePage);
        context.put("isHadNextPage", isHadNextPage);
        context.put("prePage", postQuery.getPage() - 1);
        context.put("nextPage", postQuery.getPage() + 1);
        context.put("postList", postList);
        String postListStr = VelocityUtil.mergeTemplate(context, velocityConfig.getVelocityEngine(), "/myCenter/postManger/listGroupItem.vm");
        response.setBody(postListStr);
        response.setSuccess(true);
        return response;
    }


}
