package com.fellow.web.controller.dynamics;

import com.fellow.common.constant.SystemConstant;
import com.fellow.common.util.VelocityUtil;
import com.fellow.domain.enums.AttitudeStatusEnum;
import com.fellow.domain.enums.DynamicsPropertyEnum;
import com.fellow.domain.model.PersonalDynamics;
import com.fellow.domain.model.PersonalDynamicsComment;
import com.fellow.domain.model.PersonalDynamicsUp;
import com.fellow.domain.query.PersonalDynamicsCommentQuery;
import com.fellow.domain.query.PersonalDynamicsQuery;
import com.fellow.domain.query.PersonalDynamicsUpQuery;
import com.fellow.domain.web.Response;
import com.fellow.service.PersonalDynamicsCommentService;
import com.fellow.service.PersonalDynamicsService;
import com.fellow.service.PersonalDynamicsUpService;
import com.fellow.web.base.WebAbstract;
import org.apache.commons.collections.CollectionUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by wubiao on 2017/2/22.
 */
@Controller
@RequestMapping(FriendDynamicsController.VIEW_PATH)
public class FriendDynamicsController extends WebAbstract<PersonalDynamicsService> {
    public static final String VIEW_PATH = "/dynamics/friend";

    @Resource
    private PersonalDynamicsCommentService personalDynamicsCommentService;
    @Resource
    private PersonalDynamicsUpService personalDynamicsUpService;

    @RequestMapping("/index")
    public String index() {
        return VIEW_PATH + "/index";
    }

    @RequestMapping(value = "/showUpUser")
    public String showUpUser(Model model, Long personalId) {
        model.addAttribute("personalId", personalId);
        return VIEW_PATH + "/showUpUser";
    }

    /**
     * 查点赞人
     *
     * @param upQuery
     * @return
     */
    @RequestMapping(value = "/queryUpUser")
    @ResponseBody
    public Response queryUpUser(PersonalDynamicsUpQuery upQuery) {
        Response response = Response.newResponse();
        upQuery.setPageSize(SystemConstant.DEFAULT_PAGESIZE);
        upQuery.initMysqlLimit();
        upQuery.setThumbsTypeNoEq(AttitudeStatusEnum.CANCEL.getKey());
        List<PersonalDynamicsUp> dynamicsUpList = personalDynamicsUpService.selectUpByPersonalId(upQuery);
        Context context = new VelocityContext();
        context.put("dynamicsUpList", dynamicsUpList);
        context.put("collectionUtils", new CollectionUtils());
        context.put("ThumbsTypeEnum_UP", AttitudeStatusEnum.UP.getKey());
        context.put("page", upQuery.getPage());
        String dynamicsTemplate = VelocityUtil.mergeTemplate(context, velocityConfig.getVelocityEngine(), "/dynamics/friend/upUser.vm");
        response.setBody(dynamicsTemplate);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("isHadPre", upQuery.getPage() > 1);
        resultMap.put("isHadNext", upQuery.getPageSize() == dynamicsUpList.size());
        response.setObject(resultMap);
        if (CollectionUtils.isEmpty(dynamicsUpList) && 1 < upQuery.getPage()) {
            response.setSuccess(false);
            response.setMessage("当前页为最后一页了！");
        } else {
            response.setSuccess(true);
        }
        return response;
    }

    /**
     * 动态
     *
     * @param dynamicsQuery
     * @return
     */
    @RequestMapping("/queryDynamics")
    @ResponseBody
    public Response queryDynamics(PersonalDynamicsQuery dynamicsQuery) {
        dynamicsQuery.setPageSize(SystemConstant.DEFAULT_PAGESIZE);
        dynamicsQuery.initMysqlLimit();
        Response response = Response.newResponse();
        dynamicsQuery.setAccount(super.getAccount());
        dynamicsQuery.setDynamicsProperty(DynamicsPropertyEnum.FRIEND.getKey());
        List<PersonalDynamics> dynamicsList = service.selectDynamics(dynamicsQuery);
        Context context = new VelocityContext();
        context.put("dynamicsList", dynamicsList);
        context.put("collectionUtils", new CollectionUtils());
        context.put("isFirst", dynamicsQuery.getCount() <= 1);
        context.put("COMMENT_GROUP_NUM", SystemConstant.COMMENT_GROUP_NUM);
        context.put("ThumbsTypeEnumMap", AttitudeStatusEnum.getMap());
        response.setBody(VelocityUtil.mergeTemplate(context, velocityConfig.getVelocityEngine(), VIEW_PATH + "/dynamics_list.vm"));
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("isHadNext", dynamicsQuery.getPageSize() == dynamicsList.size());
        if (CollectionUtils.isNotEmpty(dynamicsList)) {
            resultMap.put("idLt", dynamicsList.get(dynamicsList.size() - 1).getId());
        } else {
            resultMap.put("idLt", 0);
        }
        response.setObject(resultMap);
        response.setSuccess(true);
        return response;
    }

    /**
     * 评论
     *
     * @param commentQuery
     * @return
     */
    @RequestMapping("/selectPerDynCommentByPersonalId")
    @ResponseBody
    public Response selectPerDynCommentByPersonalId(PersonalDynamicsCommentQuery commentQuery) {
        commentQuery.setSortColumns("id desc");
        commentQuery.setPageSize(SystemConstant.DEFAULT_PAGESIZE);
        commentQuery.initMysqlLimit();
        Response response = Response.newResponse();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<PersonalDynamicsComment> commentList = personalDynamicsCommentService.selectPerDynCommentByPersonalId(commentQuery);
        if (CollectionUtils.isNotEmpty(commentList)) {
            Context context = new VelocityContext();
            context.put("commentList", commentList);
            String dynamicsReplyHtml = VelocityUtil.mergeTemplate(context, velocityConfig.getVelocityEngine(), "/dynamics/friend/dynamics_reply.vm");
            response.setBody(dynamicsReplyHtml);
            resultMap.put("isHadNext", commentQuery.getPageSize() == commentList.size());
            resultMap.put("idLt", commentList.get(commentList.size() - 1).getId());
        } else {
            resultMap.put("isHadNext", false);
        }
        response.setObject(resultMap);
        response.setSuccess(true);
        return response;
    }

    /**
     * 回复
     *
     * @param comment
     * @return
     */
    @RequestMapping(value = "/commentReply")
    @ResponseBody
    public Response commentReply(PersonalDynamicsComment comment) {
        Response response = Response.newResponse();
        comment.setFromAccount(super.getAccount());
        comment.setCreateAccount(super.getAccount());
        comment.setCreateName(super.getUserName());
        comment.setCreateTime(new Date());
        comment.setReplyTime(new Date());
        personalDynamicsCommentService.commentDynReply(comment);
        //表情
        String contentHtml = VelocityUtil.mergeEmoticon(comment.getContent());
        comment.setContent(contentHtml);
        List<PersonalDynamicsComment> commentList = new ArrayList<PersonalDynamicsComment>();
        commentList.add(comment);
        Context context = new VelocityContext();
        context.put("commentList", commentList);
        String dynamicsReplyHtml = VelocityUtil.mergeTemplate(context, velocityConfig.getVelocityEngine(), "/dynamics/friend/dynamics_reply.vm");
        response.setBody(dynamicsReplyHtml);
        response.setSuccess(true);
        return response;
    }

    /**
     * 点赞
     *
     * @param dynamicsUp
     * @return
     */
    @RequestMapping(value = "/thumbsDynamicsComment")
    @ResponseBody
    public Response thumbsDynamicsComment(PersonalDynamicsUp dynamicsUp) {
        Response response = Response.newResponse();
        dynamicsUp.setAccount(super.getAccount());
        dynamicsUp.setCreateAccount(super.getAccount());
        dynamicsUp.setCreateName(super.getUserName());
        dynamicsUp.setCreateTime(new Date());
        personalDynamicsUpService.thumbsDynamicsComment(dynamicsUp);
//        PersonalDynamics personalDynamics= service.getByKey(dynamicsUp.getPersonalId());
        response.setSuccess(true);
        return response;
    }


}
