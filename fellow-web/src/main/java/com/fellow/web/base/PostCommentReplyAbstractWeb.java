package com.fellow.web.base;

import com.fellow.common.db.able.GetByKeyAble;
import com.fellow.common.util.VelocityUtil;
import com.fellow.domain.enums.PostDeleteEnum;
import com.fellow.domain.enums.ReplyTypeEnum;
import com.fellow.domain.model.PostCommentReply;
import com.fellow.domain.model.post.PostDomain;
import com.fellow.domain.query.PostCommentReplyQuery;
import com.fellow.domain.web.Response;
import com.fellow.service.PostCommentReplyService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wubiao on 2016/9/24.
 */
public abstract class PostCommentReplyAbstractWeb<PostS> extends WebAbstract<PostCommentReplyService> {

    @Autowired
    private PostS postService;
    @Resource
    private PostCommentReplyService postCommentReplyService;


    public Response loadReply(PostCommentReplyQuery postCommentReplyQuery) {
        postCommentReplyQuery.setPage(1);
        postCommentReplyQuery.setSortColumns(" id desc");
        postCommentReplyQuery.initMysqlLimit();
        Response response = Response.newResponse();
        List<PostCommentReply> pageList = postCommentReplyService.selectByPostIdAndReplyIds(postCommentReplyQuery);
        StringBuffer bodyHtmlSb = new StringBuffer();
        Context context = new VelocityContext();
        context.put("replyTypeEnumMap", ReplyTypeEnum.getEnumMap());
        for (PostCommentReply replyTemp : pageList) {
            replyTemp.setContent(VelocityUtil.mergeEmoticon(replyTemp.getContent()));
            context.put("commentReply", replyTemp);
            String replyCommentBody = VelocityUtil.mergeTemplate(context, velocityConfig.getVelocityEngine(), "/post/post/commentReplyList.vm");
            bodyHtmlSb.append(replyCommentBody);
        }
        Map<Object, Object> resultMap = new HashMap<Object, Object>();
        boolean hasNextPage = CollectionUtils.isNotEmpty(pageList) && pageList.size() == postCommentReplyQuery.getPageSize() ? true : false;
        Long maxId = CollectionUtils.isNotEmpty(pageList) ? pageList.get(pageList.size() - 1).getId() : null;
        resultMap.put("hasNextPage", hasNextPage);
        resultMap.put("maxId", maxId);
        response.setBody(bodyHtmlSb.toString());
        response.setObject(resultMap);
        response.setSuccess(true);
        return response;
    }


    public Response reply(PostCommentReply postCommentReply) {
        Response response = Response.newResponse();
        try {
            PostDomain post = (PostDomain) ((GetByKeyAble) postService).getByKey(postCommentReply.getPostId());
            postCommentReply.setTitle(post.getTitle());
            postCommentReply.setFromAccount(super.getAccount());
            postCommentReply.setUpNum(0);
            postCommentReply.setDownNum(0);
            postCommentReply.setLoveNum(0);
            postCommentReply.setReplyTime(new Date());
            postCommentReply.setPostDelete(PostDeleteEnum.LOST_NOT_DELETE.getKey());
            postCommentReply.setFromDelete(PostDeleteEnum.LOST_NOT_DELETE.getKey());
            postCommentReply.setToDelete(PostDeleteEnum.LOST_NOT_DELETE.getKey());
            postCommentReply.setCreateAccount(super.getAccount());
            postCommentReply.setCreateName(super.getUserName());
            postCommentReply.setCreateTime(new Date());
            replyWrap(postCommentReply);
            postCommentReplyService.reply(postCommentReply);
            postCommentReply.setContent(VelocityUtil.mergeEmoticon(postCommentReply.getContent()));
            Context context = new VelocityContext();
            context.put("commentReply", postCommentReply);
            context.put("replyTypeEnumMap", ReplyTypeEnum.getEnumMap());
            String replyCommentBody = VelocityUtil.mergeTemplate(context, velocityConfig.getVelocityEngine(), "/post/post/commentReplyList.vm");
            response.setBody(replyCommentBody);
            response.setSuccess(true);
        } catch (Exception e) {
            log.info("reply--回复失败--e=", e);
        }
        return response;
    }

    protected void replyWrap(PostCommentReply postCommentReply) {
    }

    public Response deleteReplyById(PostCommentReply postCommentReply) {
        Response response = Response.newResponse();
        postCommentReply.setFromAccount(super.getAccount());
        postCommentReply.setUpdateAccount(super.getAccount());
        postCommentReply.setUpdateName(super.getUserName());
        service.deleteReplyById(postCommentReply);
        response.setSuccess(true);
        return response;
    }

    ;

}
