package com.fellow.web.base;

import com.fellow.common.constant.SystemConstant;
import com.fellow.common.db.able.post.PostReplyAble;
import com.fellow.common.util.PostSortUitil;
import com.fellow.common.util.VelocityUtil;
import com.fellow.domain.enums.PostImgTypeEnum;
import com.fellow.domain.enums.ReplyTypeEnum;
import com.fellow.domain.model.LostImg;
import com.fellow.domain.model.PostCommentReply;
import com.fellow.domain.model.post.ImgDomain;
import com.fellow.domain.model.post.PostReplyDomain;
import com.fellow.domain.mybatis.PaginatorImpl;
import com.fellow.domain.query.post.PostReplyQuery;
import com.fellow.domain.web.Response;
import org.apache.commons.lang.ArrayUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wubiao on 2017/1/13.
 */
public class PostReplyAbstractWeb<S> extends WebAbstract<S> {

    public Response reply(PostReplyDomain postReply, String[] imgStrs) {
        Response response = Response.newResponse();
        postReply.setReplyTime(new Date());
        postReply.setReplyAccount(super.getAccount());
        postReply.setImgList(new ArrayList<ImgDomain>());
        postReply.setCreateAccount(super.getAccount());
        postReply.setCreateName(super.getUserName());
        if (ArrayUtils.isNotEmpty(imgStrs)) {
            if (imgStrs.length > SystemConstant.IMAGE_UPLOAD_MAX_NUM) {
                response.setMessage("图片不能大于" + SystemConstant.IMAGE_UPLOAD_MAX_NUM + "张");
                return response;
            }
            ImgDomain imgTemp;
            String imgRootPathTemp = "";
            String imgRelativePathTemp = "";
            for (int i = 0; i < imgStrs.length; i++) {
                String srcTemp = imgStrs[i];
                if (srcTemp.startsWith("/")) {
                    imgRootPathTemp = srcTemp.substring(0, 1);
                    imgRelativePathTemp = srcTemp.substring(1);
                } else if (srcTemp.startsWith("http")) {
                    int fromIndex = srcTemp.indexOf("//") + 2;
                    int beginIndex = srcTemp.indexOf("/", fromIndex);
                    imgRootPathTemp = srcTemp.substring(0, beginIndex);
                    imgRelativePathTemp = srcTemp.substring(beginIndex + 1);
                }
                String imgName = "name_img_" + i;
                imgTemp = new LostImg();
                imgTemp.setPostId(postReply.getPostId());
                imgTemp.setImgType(PostImgTypeEnum.REPLY.getKey());
                imgTemp.setAccount(super.getAccount());
                imgTemp.setImgRootPath(imgRootPathTemp);
                imgTemp.setImgRelativePath(imgRelativePathTemp);
                imgTemp.setImgName(imgName);
                imgTemp.setCreateAccount(super.getAccount());
                imgTemp.setCreateName(super.getUserName());
                postReply.getImgList().add(imgTemp);
            }
            postReply.setImgNum(imgStrs.length);
        }
        ((PostReplyAble) service).reply(postReply);
        response.setSuccess(true);
        return response;
    }

    public String queryReply(PostReplyQuery replyQuery, Model model) {
        String sortColumns = replyQuery.getSortColumns();
        replyQuery.setSortColumns(PostSortUitil.getDefaultSortField(replyQuery.getSortColumns()));
        replyQuery.initMysqlLimit();
        List<PostReplyDomain> lostPostReplyList = ((PostReplyAble) service).queryReply(replyQuery);
        long totalCount = ((PostReplyAble) service).queryReplyCount(replyQuery);
        PaginatorImpl paginator = new PaginatorImpl(replyQuery.getPage(), SystemConstant.DEFAULT_PAGESIZE, (int) totalCount);
        model.addAttribute("postReplyList", lostPostReplyList);
        model.addAttribute("replyPaginator", paginator);
        model.addAttribute("sortColumns", sortColumns);
        model.addAttribute("replyTypeEnumMap", ReplyTypeEnum.getEnumMap());
        return getModule() + "/postReply";
    }

    public Response queryReplyCount(PostReplyQuery replyQuery) {
        Response response = Response.newResponse();
        long totalCount = ((PostReplyAble) service).queryReplyCount(replyQuery);
        PaginatorImpl paginator = new PaginatorImpl(replyQuery.getPage(), SystemConstant.DEFAULT_PAGESIZE, (int) totalCount);
        Context context = new VelocityContext();
        context.put("paginatorImpl", paginator);
        String paginatorString = VelocityUtil.mergeTemplate(context, velocityConfig.getVelocityEngine(), "/post/reply_paginator.vm");
        response.setBody(paginatorString);
        response.setSuccess(true);
        return response;
    }

    public Response deleteReplyByAccount(PostReplyDomain postReplyDomain) {
        Response response = Response.newResponse();
        postReplyDomain.setReplyAccount(super.getAccount());
        postReplyDomain.setUpdateAccount(super.getAccount());
        postReplyDomain.setUpdateName(super.getUserName());

        PostCommentReply postCommentReply = new PostCommentReply();
        postCommentReply.setPostId(postReplyDomain.getPostId());
        postCommentReply.setReplyId(postReplyDomain.getId());
//        postCommentReply.setFromAccount(super.getAccount());
        postCommentReply.setUpdateAccount(super.getAccount());
        postCommentReply.setUpdateName(super.getUserName());
        deleteCommentReplyWrap(postCommentReply);
        postReplyDomain.setPostCommentReply(postCommentReply);
        ((PostReplyAble) service).deleteReplyByAccount(postReplyDomain);
        response.setSuccess(true);
        return response;
    }

    protected void deleteCommentReplyWrap(PostCommentReply postCommentReply) {

    }


}
