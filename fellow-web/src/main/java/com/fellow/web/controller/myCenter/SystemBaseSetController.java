package com.fellow.web.controller.myCenter;

import com.fellow.domain.enums.*;
import com.fellow.domain.model.SystemBaseSet;
import com.fellow.domain.query.SystemBaseSetQuery;
import com.fellow.domain.web.Response;
import com.fellow.service.SystemBaseSetService;
import com.fellow.web.base.WebAbstract;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by wubiao on 2017/2/22.
 */
@Controller
@RequestMapping(SystemBaseSetController.VIEW_PATH)
public class SystemBaseSetController extends WebAbstract<SystemBaseSetService> {
    public static final String VIEW_PATH = "/myCenter/systemBaseSet";

    @RequestMapping(value = "/index")
    public String index(Model model) {
        SystemBaseSetQuery systemBaseSetQuery = new SystemBaseSetQuery();
        systemBaseSetQuery.setAccount(super.getAccount());
        SystemBaseSet systemBaseSet = service.selectByAccount(systemBaseSetQuery);
        //如果为空则走默认权限
        if (null == systemBaseSet) {
            systemBaseSet = new SystemBaseSet();
            systemBaseSet.setAccount(super.getAccount());
            systemBaseSet.setStrangerMessage(StrangerMessageEnum.RECEIVE.getKey());
            systemBaseSet.setDynAuthority(DynAuthorityEnum.getDefault().getKey());
            systemBaseSet.setMeAuthority(MeAuthorityEnum.getDefault().getKey());
            systemBaseSet.setInfoAuthority(InfoAuthorityEnum.getDefault().getKey());
            systemBaseSet.setShowDyn(ShowDynEnum.getDefaultShowDynEnum().getKey());
        }
        model.addAttribute("strangerReceiveValue", StrangerMessageEnum.RECEIVE.getKey());
        model.addAttribute("dynAuthorityEnums", DynAuthorityEnum.values());
        model.addAttribute("meAuthorityEnums", MeAuthorityEnum.values());
        model.addAttribute("infoAuthorityEnums", InfoAuthorityEnum.values());
        model.addAttribute("showDynEnums", ShowDynEnum.values());
        model.addAttribute("baseSetInfo", systemBaseSet);
        return VIEW_PATH + "/index";
    }

    @RequestMapping(value = "/updateSystemBaseSet", method = RequestMethod.POST)
    @ResponseBody
    public Response updateSystemBaseSet(SystemBaseSet systemBaseSet) {
        Response response = Response.newResponse();
        if (null == systemBaseSet.getStrangerMessage()){
            systemBaseSet.setStrangerMessage(StrangerMessageEnum.REFUSE.getKey());
        }
        systemBaseSet.setAccount(super.getAccount());
        systemBaseSet.setCreateAccount(super.getAccount());
        systemBaseSet.setCreateName(super.getAccount());
        systemBaseSet.setCreateTime(new Date());
        service.updateBaseInfo(systemBaseSet);
        response.setSuccess(true);
        response.setMessage("信息修改成功！");
        return response;
    }


}
