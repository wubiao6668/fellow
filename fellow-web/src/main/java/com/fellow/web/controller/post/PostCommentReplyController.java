package com.fellow.web.controller.post;

import com.fellow.service.PostCommentReplyService;
import com.fellow.web.base.WebAbstract;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wubiao on 2016/9/24.
 */
@Controller
@RequestMapping(PostCommentReplyController.VIEW_PATH)
public class PostCommentReplyController extends WebAbstract<PostCommentReplyService> {
    public static final String VIEW_PATH = "/post/postCommentReply";

}
