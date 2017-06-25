package com.fellow.web.controller;

import com.fellow.common.util.UserUtil;
import com.fellow.domain.enums.*;
import com.fellow.domain.model.UserDetail;
import com.fellow.domain.query.UserDetailQuery;
import com.fellow.domain.web.Response;
import com.fellow.service.UserDetailService;
import com.fellow.service.UserService;
import com.fellow.service.cache.MajorCacheService;
import com.fellow.web.base.WebAbstract;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

/**
 * Created by wubiao on 2016/11/15.
 */
@Controller
@RequestMapping(UserLookForController.VIEW_PATH)
public class UserLookForController extends WebAbstract<UserService> {
    public static final String VIEW_PATH = "/user/lookFor";
    @Resource
    private UserDetailService userDetailService;
    @Resource
    private MajorCacheService majorCacheService;

    @RequestMapping(value = "/index")
    public String index(Model model) {
        model.addAttribute("sexEnums", SexEnum.values());
        model.addAttribute("relationshipStatusEnums", RelationshipStatusEnum.values());
        model.addAttribute("educationEnums", EducationEnum.values());
        model.addAttribute("monthlyIncomeEnums", MonthlyIncomeEnum.values());
        model.addAttribute("professionEnums", ProfessionEnum.values());
        return VIEW_PATH + "/index";
    }

    @RequestMapping(value = "findUser")
    @ResponseBody
    public Response findUser(UserDetailQuery userDetailQuery) {
        userDetailQuery.setPageSize(1);
        userDetailQuery.initMysqlLimit();
        Response response = new Response();
        List<UserDetail> userDetailList = userDetailService.findUser(userDetailQuery);
        boolean isHadPrePage = false;
        boolean isHadNextPage = false;
        isHadPrePage = 1 >= userDetailQuery.getPage() ? false : true;
        if (CollectionUtils.isNotEmpty(userDetailList)) {
            for (UserDetail userDetailTemp : userDetailList) {
                userDetailTemp.setAge(UserUtil.calcAge(userDetailTemp.getBirthday()));
                userDetailTemp.setProfessionText(ProfessionEnum.getValueByKey(userDetailTemp.getProfession()));
                if (null != userDetailTemp.getMajorId()) {
                    userDetailTemp.setMajorText(majorCacheService.getMajorNameById(userDetailTemp.getMajorId()));
                }
            }
            if (userDetailList.size() == userDetailQuery.getPageSize()) {
                isHadNextPage = true;
            }
            VelocityEngine velocityEngine = velocityConfig.getVelocityEngine();
            Context context = new VelocityContext();
            context.put("collectionUtils", new CollectionUtils());
            context.put("stringUtils", new StringUtils());
            context.put("userDetailList", userDetailList);
            context.put("isHadPrePage", isHadPrePage);
            context.put("isHadNextPage", isHadNextPage);
            context.put("prePage", userDetailQuery.getPage() - 1);
            context.put("nextPage", userDetailQuery.getPage() + 1);
            Writer writer = new StringWriter();
            velocityEngine.mergeTemplate("/user/lookFor/user_list.vm", "UTF-8", context, writer);
            response.setBody(writer.toString());
            response.setSuccess(true);
        } else {
            if (1 != userDetailQuery.getPage()) {
                response.setSuccess(false);
                response.setMessage("当前页已经是最后一页了。");
            } else {
                VelocityEngine velocityEngine = velocityConfig.getVelocityEngine();
                Context context = new VelocityContext();
                context.put("collectionUtils", new CollectionUtils());
                context.put("stringUtils", new StringUtils());
                context.put("userDetailList", userDetailList);
                context.put("isHadPrePage", isHadPrePage);
                context.put("isHadNextPage", isHadNextPage);
                context.put("prePage", userDetailQuery.getPage() - 1);
                context.put("nextPage", userDetailQuery.getPage() + 1);
                Writer writer = new StringWriter();
                velocityEngine.mergeTemplate("/user/lookFor/user_list.vm", "UTF-8", context, writer);
                response.setBody(writer.toString());
                response.setSuccess(true);
            }
        }
        return response;
    }


}
