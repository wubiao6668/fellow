package com.fellow.web.controller.commen;

import com.fellow.domain.model.CfgSkill;
import com.fellow.domain.query.CfgSkillQuery;
import com.fellow.domain.web.Response;
import com.fellow.service.CfgSkillService;
import com.fellow.web.base.WebAbstract;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wubiao on 2017/2/23.
 */
@Controller
@RequestMapping(CfgSkillController.VIEW_PATH)
public class CfgSkillController extends WebAbstract<CfgSkillService> {
    public static final String VIEW_PATH = "/common/cfgSkill";

    @RequestMapping(value = "/selectSkillByUpid")
    @ResponseBody
    public Response selectSkillByUpid(CfgSkillQuery cfgSkillQuery) {
        Response response = Response.newResponse();
        List<CfgSkill> cfgSkillList = service.selectSkillByUpid(cfgSkillQuery);
        response.setBody(cfgSkillList);
        response.setSuccess(true);
        return response;
    }

}
