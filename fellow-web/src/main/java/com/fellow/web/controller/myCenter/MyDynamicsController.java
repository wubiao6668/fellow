package com.fellow.web.controller.myCenter;

import com.fellow.common.exception.BusinessException;
import com.fellow.common.util.VelocityUtil;
import com.fellow.domain.model.PersonalDynamicsComment;
import com.fellow.domain.query.PersonalDynamicsCommentQuery;
import com.fellow.domain.vo.PersonalDynamicsCommentVo;
import com.fellow.domain.web.Response;
import com.fellow.service.PersonalDynamicsCommentService;
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
@RequestMapping(MyDynamicsController.VIEW_PATH)
public class MyDynamicsController extends WebAbstract<PersonalDynamicsCommentService> {
    public static final String VIEW_PATH = "/myCenter/myDynamics";

    @RequestMapping("/index")
    public String index(Model model) {
        PersonalDynamicsCommentQuery dynCommentQuery = new PersonalDynamicsCommentQuery();
        dynCommentQuery.setPage(1);
        dynCommentQuery.setPageSize(10);
        dynCommentQuery.initMysqlLimit();
        dynCommentQuery.setSortColumns("id desc");
        dynCommentQuery.setToAccount(super.getAccount());
        dynCommentQuery.setFromAccount(super.getAccount());
        List<PersonalDynamicsCommentVo> dynCommentList = service.selectPerDynCommentAndSetHadRead(dynCommentQuery);
        boolean isHadPrePage = 1 >= dynCommentQuery.getPage() ? false : true;
        boolean isHadNextPage = false;
        if (CollectionUtils.isNotEmpty(dynCommentList)) {
            for (PersonalDynamicsCommentVo dynCommentTemp : dynCommentList) {
                dynCommentTemp.setContent(VelocityUtil.mergeEmoticon(velocityConfig.getVelocityEngine(), dynCommentTemp.getContent()));
                dynCommentTemp.setOriginalContent(VelocityUtil.mergeEmoticon(velocityConfig.getVelocityEngine(), dynCommentTemp.getOriginalContent()));
            }
            isHadNextPage = dynCommentList.size() < dynCommentQuery.getPageSize() ? false : true;
        }
        model.addAttribute("isHadPrePage", isHadPrePage);
        model.addAttribute("isHadNextPage", isHadNextPage);
        model.addAttribute("prePage", dynCommentQuery.getPage() - 1);
        model.addAttribute("nextPage", dynCommentQuery.getPage() + 1);
        model.addAttribute("dynCommentList", dynCommentList);
        return VIEW_PATH + "/index";
    }


    @RequestMapping(value = "/queryDynCommentToMe")
    @ResponseBody
    public Response queryDynCommentToMe(PersonalDynamicsCommentQuery dynCommentQuery) {
        Response response = Response.newResponse();
        dynCommentQuery.setPageSize(10);
        dynCommentQuery.initMysqlLimit();
        dynCommentQuery.setSortColumns("id desc");
        dynCommentQuery.setToAccount(super.getAccount());
        dynCommentQuery.setFromAccount(super.getAccount());
        List<PersonalDynamicsCommentVo> dynCommentList = service.selectPerDynCommentAndSetHadRead(dynCommentQuery);
        boolean isHadPrePage = 1 >= dynCommentQuery.getPage() ? false : true;
        boolean isHadNextPage = false;
        if (CollectionUtils.isNotEmpty(dynCommentList)) {
            for (PersonalDynamicsCommentVo dynCommentTemp : dynCommentList) {
                dynCommentTemp.setContent(VelocityUtil.mergeEmoticon(velocityConfig.getVelocityEngine(), dynCommentTemp.getContent()));
                dynCommentTemp.setOriginalContent(VelocityUtil.mergeEmoticon(velocityConfig.getVelocityEngine(), dynCommentTemp.getOriginalContent()));
            }
            isHadNextPage = dynCommentList.size() < dynCommentQuery.getPageSize() ? false : true;
        } else {
            if (1 < dynCommentQuery.getPage()) {
                response.setMessage("已经是最后一页了!");
                return response;
            }
        }
        Context context = new VelocityContext();
        context.put("collectionUtils", new CollectionUtils());
        context.put("account", getAccount());
        context.put("isHadPrePage", isHadPrePage);
        context.put("isHadNextPage", isHadNextPage);
        context.put("prePage", dynCommentQuery.getPage() - 1);
        context.put("nextPage", dynCommentQuery.getPage() + 1);
        context.put("dynCommentList", dynCommentList);
        String postListStr = VelocityUtil.mergeTemplate(context, velocityConfig.getVelocityEngine(), "/myCenter/myDynamics/dyCommentList.vm");
        response.setBody(postListStr);
        response.setSuccess(true);
        return response;
    }

    @RequestMapping(value = "/deleteDynCommentById")
    @ResponseBody
    public Response deleteDynCommentById(PersonalDynamicsComment dynCommentReply) {
        Response response = Response.newResponse();
        if (null == dynCommentReply.getId()) {
            response.setMessage("删除失败！");
            return response;
        }
        dynCommentReply.setUpdateAccount(super.getAccount());
        dynCommentReply.setUpdateName(super.getUserName());
        try {
            service.deleteDynCommentById(dynCommentReply);
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
