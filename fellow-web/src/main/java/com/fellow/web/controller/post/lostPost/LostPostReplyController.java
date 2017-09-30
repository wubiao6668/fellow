package com.fellow.web.controller.post.lostPost;

import com.fellow.domain.constant.SystemConstant;
import com.fellow.domain.enums.PostEnum;
import com.fellow.domain.model.LostPostReply;
import com.fellow.domain.model.PostCommentReply;
import com.fellow.domain.model.post.PostReplyDomain;
import com.fellow.domain.query.LostPostReplyQuery;
import com.fellow.domain.query.post.PostReplyQuery;
import com.fellow.domain.web.Response;
import com.fellow.service.LostPostReplyService;
import com.fellow.web.base.PostReplyAbstractWeb;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wubiao on 2016/9/18.
 */
@Controller
@RequestMapping(LostPostReplyController.VIEW_PATH)
public class LostPostReplyController extends PostReplyAbstractWeb<LostPostReplyService> {
    public static final String VIEW_PATH = "/post/lost/postReply";

    @RequestMapping(value = "/reply", method = RequestMethod.POST)
    @ResponseBody
    public Response reply(LostPostReply postReply, String[] imgStrs) {
        return super.reply(postReply, imgStrs);
    }

    @RequestMapping(value = "queryReply", method = RequestMethod.POST)
    public String queryReply(LostPostReplyQuery replyQuery, Model model) {
        replyQuery.setPageSize(SystemConstant.DEFAULT_PAGESIZE);
        return super.queryReply(replyQuery, model);
    }

    @RequestMapping(value = "queryReplyCount", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    @Override
    public Response queryReplyCount(PostReplyQuery replyQuery) {
        return super.queryReplyCount(replyQuery);
    }


    @RequestMapping(value = "deleteReplyByAccount", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    @Override
    public Response deleteReplyByAccount(PostReplyDomain postReplyDomain) {
        return super.deleteReplyByAccount(postReplyDomain);
    }

    @Override
    protected void deleteCommentReplyWrap(PostCommentReply postCommentReply) {
        postCommentReply.setPostType(PostEnum.LOST_POST.getKey());
    }
}






























