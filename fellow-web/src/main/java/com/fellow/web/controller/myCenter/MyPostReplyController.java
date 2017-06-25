package com.fellow.web.controller.myCenter;

import com.fellow.common.constant.SystemConstant;
import com.fellow.common.exception.BusinessException;
import com.fellow.domain.model.LostPostReply;
import com.fellow.domain.model.PostCommentReply;
import com.fellow.domain.mybatis.PaginatorImpl;
import com.fellow.domain.query.LostPostReplyQuery;
import com.fellow.domain.vo.LostPostReplyVo;
import com.fellow.domain.web.Response;
import com.fellow.service.LostPostReplyService;
import com.fellow.web.base.WebAbstract;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wubiao on 2017/3/30.
 */
@Controller
@RequestMapping(MyPostReplyController.VIEW_PATH)
public class MyPostReplyController extends WebAbstract<LostPostReplyService> {
    public static final String VIEW_PATH = "/myCenter/myPostReply";


    @RequestMapping("/index")
    public String index(LostPostReplyQuery replyQuery, Model model) {
        replyQuery.setPageSize(SystemConstant.DEFAULT_PAGESIZE);
        replyQuery.setReplyAccount(super.getAccount());
        replyQuery.initMysqlLimit();
        List<LostPostReplyVo> lostPostReplyList =  service.selectReplyAccount(replyQuery);
        long totalCount = service.selectReplyAccountCount(replyQuery);
        PaginatorImpl paginator = new PaginatorImpl(replyQuery.getPage(), SystemConstant.DEFAULT_PAGESIZE, (int) totalCount);
        model.addAttribute("postReplyList", lostPostReplyList);
        model.addAttribute("replyPaginator", paginator);
        return VIEW_PATH + "/index";
    }

    @RequestMapping(value = "/deleteByAccount")
    @ResponseBody
    public Response deleteByAccount(LostPostReply postReply){
        Response response = Response.newResponse();

        postReply.setReplyAccount(super.getAccount());
        postReply.setUpdateAccount(super.getAccount());
        postReply.setUpdateName(super.getUserName());
        PostCommentReply postCommentReply = new PostCommentReply();
        postCommentReply.setUpdateAccount(super.getAccount());
        postCommentReply.setUpdateName(super.getUserName());
        postCommentReply.setPostId(postReply.getPostId());
        postCommentReply.setReplyId(postReply.getId());
        postReply.setPostCommentReply(postCommentReply);
        //删除
        try {
            service.deleteReplyByAccount(postReply);
            response.setSuccess(true);
        }catch (BusinessException e) {
            response.setMessage(e.getMessage());
            log.error("deleteByAccount--e=",e);
        }
        catch (Exception e) {
            log.error("deleteByAccount--e=",e);
        }
        return response;
    }

}
