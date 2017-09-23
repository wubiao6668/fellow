package com.fellow.web.controller.personalHome;

import com.fellow.common.constant.SystemConstant;
import com.fellow.common.util.VelocityUtil;
import com.fellow.domain.enums.AttitudeStatusEnum;
import com.fellow.domain.enums.DynamicsPropertyEnum;
import com.fellow.domain.enums.DynamicsTypeEnum;
import com.fellow.domain.enums.PostImgTypeEnum;
import com.fellow.domain.model.DynamicsImg;
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
import org.apache.commons.lang.ArrayUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by wubiao on 2016/10/9.
 */
@Controller
@RequestMapping(PersonalDynamicsController.VIEW_PATH)
public class PersonalDynamicsController extends WebAbstract<PersonalDynamicsService> {
    public static final String VIEW_PATH = "/personal/home/dynamics";

    @Resource
    private VelocityConfigurer velocityConfig;
    @Resource
    private PersonalDynamicsUpService personalDynamicsUpService;
    @Resource
    private PersonalDynamicsCommentService personalDynamicsCommentService;


    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    @ResponseBody
    public Response publish(PersonalDynamics personalDynamics, String[] imgStrs) {
        Response response = Response.newResponse();
        personalDynamics.setAccount(super.getAccount());
        personalDynamics.setPublishTime(new Date());
        personalDynamics.setPublishType(DynamicsTypeEnum.DYNAMICS.getKey());
        personalDynamics.setCreateAccount(super.getAccount());
        personalDynamics.setDynamicsImgList(new ArrayList<DynamicsImg>());
        if (ArrayUtils.isNotEmpty(imgStrs)) {
            if (imgStrs.length > SystemConstant.IMAGE_UPLOAD_MAX_NUM) {
                response.setMessage("图片不能大于" + SystemConstant.IMAGE_UPLOAD_MAX_NUM + "张");
                return response;
            }
            DynamicsImg dynamicsImgTemp;
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
                dynamicsImgTemp = new DynamicsImg();
                dynamicsImgTemp.setImgType(PostImgTypeEnum.POST.getKey());
                dynamicsImgTemp.setAccount(super.getAccount());
                dynamicsImgTemp.setImgRootPath(imgRootPathTemp);
                dynamicsImgTemp.setImgRelativePath(imgRelativePathTemp);
                dynamicsImgTemp.setImgName(imgName);
                dynamicsImgTemp.setCreateAccount(super.getAccount());
                dynamicsImgTemp.setCreateName(super.getUserName());
                personalDynamics.getDynamicsImgList().add(dynamicsImgTemp);
            }
            personalDynamics.setImgNum(imgStrs.length);
        }
        service.publishDynamics(personalDynamics);
        response.setSuccess(true);
        return response;
    }

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
        dynamicsQuery.setDynamicsProperty(DynamicsPropertyEnum.PERAONAL.getKey());
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

















