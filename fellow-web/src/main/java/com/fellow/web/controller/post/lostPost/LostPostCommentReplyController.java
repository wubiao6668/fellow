package com.fellow.web.controller.post.lostPost;

import com.fellow.domain.enums.PostEnum;
import com.fellow.domain.model.PostCommentReply;
import com.fellow.domain.query.PostCommentReplyQuery;
import com.fellow.domain.web.Response;
import com.fellow.service.LostPostService;
import com.fellow.web.base.PostCommentReplyAbstractWeb;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wubiao on 2016/9/1.
 */
@Controller
@RequestMapping(LostPostCommentReplyController.VIEW_PATH)
public class LostPostCommentReplyController extends PostCommentReplyAbstractWeb<LostPostService> {
    public static final String VIEW_PATH = "/post/lost/postCommentReply";

    @RequestMapping(value = "/loadReply", method = RequestMethod.POST)
    @ResponseBody
    public Response loadReply(PostCommentReplyQuery postCommentReplyQuery) {
        return super.loadReply(postCommentReplyQuery);
    }

    @RequestMapping(value = "/reply", method = {RequestMethod.POST})
    @ResponseBody
    public Response reply(PostCommentReply postCommentReply) {
        return super.reply(postCommentReply);
    }

    @RequestMapping(value = "/deleteReplyById", method = {RequestMethod.POST})
    @ResponseBody
    @Override
    public Response deleteReplyById(PostCommentReply postCommentReply) {
        return super.deleteReplyById(postCommentReply);
    }

    @Override
    protected void replyWrap(PostCommentReply postCommentReply) {
        postCommentReply.setPostType(PostEnum.LOST_POST.getKey());
    }

}
