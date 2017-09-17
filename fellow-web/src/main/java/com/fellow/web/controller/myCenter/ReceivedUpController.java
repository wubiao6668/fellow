package com.fellow.web.controller.myCenter;

import com.fellow.service.AttitudeInfoService;
import com.fellow.web.base.WebAbstract;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wubiao on 17/9/2017.
 */
@Controller
@RequestMapping(ReceivedUpController.VIEW_PATH)
public class ReceivedUpController  extends WebAbstract<AttitudeInfoService> {
    public static final String VIEW_PATH = "/myCenter/receivedUp";

    @RequestMapping("/index")
    public String index(){
        return VIEW_PATH + "/index";
    }

}
