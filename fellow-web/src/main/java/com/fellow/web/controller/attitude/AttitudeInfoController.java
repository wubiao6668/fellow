package com.fellow.web.controller.attitude;

import com.fellow.domain.enums.AttitudeStatusEnum;
import com.fellow.domain.enums.AttitudeTypeEnum;
import com.fellow.domain.model.AttitudeInfo;
import com.fellow.domain.web.Response;
import com.fellow.service.AttitudeInfoService;
import com.fellow.web.base.WebAbstract;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wubiao on 27/9/2017.
 */

@Controller
@RequestMapping(AttitudeInfoController.VIEW_PATH)
public class AttitudeInfoController extends WebAbstract<AttitudeInfoService> {
    public static final String VIEW_PATH = "/attitude";

    @RequestMapping("/my")
    @ResponseBody
    public Response myAttitude(AttitudeInfo attitudeInfo) {
        Response response = Response.newResponse();
        if (!AttitudeTypeEnum.containsType(attitudeInfo.getType())) {
            return response;
        }
        if (!AttitudeStatusEnum.containsStatus(attitudeInfo.getStatus())) {
            return response;
        }
        boolean successFlag = service.myAttitude(attitudeInfo);
        response.setSuccess(successFlag);
        return response;
    }


}
