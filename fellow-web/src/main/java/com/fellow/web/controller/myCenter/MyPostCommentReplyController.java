package com.fellow.web.controller.myCenter;

import com.fellow.common.exception.BusinessException;
import com.fellow.common.util.VelocityUtil;
import com.fellow.domain.enums.PostEnum;
import com.fellow.domain.model.PostCommentReply;
import com.fellow.domain.query.PostCommentReplyQuery;
import com.fellow.domain.vo.PostCommentReplyVo;
import com.fellow.domain.web.Response;
import com.fellow.service.PostCommentReplyService;
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
 * Created by wubiao on 2016/11/23.
 */
@Controller
@RequestMapping(MyPostCommentReplyController.VIEW_PATH)
public class MyPostCommentReplyController extends WebAbstract<PostCommentReplyService> {
    public static final String VIEW_PATH = "/myCenter/commentReply";

    @RequestMapping("/index")
    public String index(Model model) {
        PostCommentReplyQuery postCommentReplyQuery = new PostCommentReplyQuery();
        postCommentReplyQuery.setPage(1);
        postCommentReplyQuery.setPageSize(10);
        postCommentReplyQuery.initMysqlLimit();
        postCommentReplyQuery.setSortColumns("id desc");
        postCommentReplyQuery.setToAccount(super.getAccount());
        postCommentReplyQuery.setFromAccount(super.getAccount());
        List<PostCommentReplyVo> postCommentReplyList = service.selectReplyMeCommentAndSetHadRead(postCommentReplyQuery);
        boolean isHadPrePage = 1 >= postCommentReplyQuery.getPage() ? false : true;
        boolean isHadNextPage = false;
        if (CollectionUtils.isNotEmpty(postCommentReplyList)) {
            for (PostCommentReply replyTemp : postCommentReplyList) {
                replyTemp.setPostTypeString(PostEnum.getValueByKey(replyTemp.getPostType()));
                replyTemp.setContent(VelocityUtil.mergeEmoticon(velocityConfig.getVelocityEngine(), replyTemp.getContent()));
            }
            isHadNextPage = postCommentReplyList.size() < postCommentReplyQuery.getPageSize() ? false : true;
        }
        model.addAttribute("isHadPrePage", isHadPrePage);
        model.addAttribute("isHadNextPage", isHadNextPage);
        model.addAttribute("prePage", postCommentReplyQuery.getPage() - 1);
        model.addAttribute("nextPage", postCommentReplyQuery.getPage() + 1);
        model.addAttribute("postCommentReplyList", postCommentReplyList);
        return VIEW_PATH + "/index";
    }


    @RequestMapping(value = "/queryReplyToMe")
    @ResponseBody
    public Response queryReplyToMe(PostCommentReplyQuery postCommentReplyQuery) {
        Response response = Response.newResponse();
        postCommentReplyQuery.setPageSize(10);
        postCommentReplyQuery.initMysqlLimit();
        postCommentReplyQuery.setSortColumns("id desc");
        postCommentReplyQuery.setToAccount(super.getAccount());
        postCommentReplyQuery.setFromAccount(super.getAccount());
        List<PostCommentReplyVo> postCommentReplyList = service.selectReplyMeCommentAndSetHadRead(postCommentReplyQuery);
        boolean isHadPrePage = 1 >= postCommentReplyQuery.getPage() ? false : true;
        boolean isHadNextPage = false;
        if (CollectionUtils.isNotEmpty(postCommentReplyList)) {
            for (PostCommentReply replyTemp : postCommentReplyList) {
                replyTemp.setPostTypeString(PostEnum.getValueByKey(replyTemp.getPostType()));
                replyTemp.setContent(VelocityUtil.mergeEmoticon(velocityConfig.getVelocityEngine(), replyTemp.getContent()));
            }
            isHadNextPage = postCommentReplyList.size() < postCommentReplyQuery.getPageSize() ? false : true;
        } else {
            if (1 < postCommentReplyQuery.getPage()) {
                response.setMessage("已经是最后一页了!");
                return response;
            }
        }
        Context context = new VelocityContext();
        context.put("collectionUtils", new CollectionUtils());
        context.put("account", getAccount());
        context.put("isHadPrePage", isHadPrePage);
        context.put("isHadNextPage", isHadNextPage);
        context.put("prePage", postCommentReplyQuery.getPage() - 1);
        context.put("nextPage", postCommentReplyQuery.getPage() + 1);
        context.put("postCommentReplyList", postCommentReplyList);
        String postListStr = VelocityUtil.mergeTemplate(context, velocityConfig.getVelocityEngine(), "/myCenter/commentReply/replyList.vm");
        response.setBody(postListStr);
        response.setSuccess(true);
        return response;
    }

    @RequestMapping(value = "/deleteCommentById")
    @ResponseBody
    public Response deleteCommentById(PostCommentReply postCommentReply) {
        Response response = Response.newResponse();
        if (null == postCommentReply.getId()) {
            response.setMessage("删除失败！");
            return response;
        }
        postCommentReply.setUpdateAccount(super.getAccount());
        postCommentReply.setUpdateName(super.getUserName());
        try {
            service.deleteCommentById(postCommentReply);
            response.setSuccess(true);
        } catch (BusinessException e) {
            response.setMessage(e.getMessage());
            log.error("deleteCommentById--删除评论异常--e=", e);
        } catch (Exception e) {
            response.setMessage("删除失败！");
            log.error("deleteCommentById--删除评论异常--e=", e);
        }
        return response;
    }

}
