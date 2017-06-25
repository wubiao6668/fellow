package com.fellow.web.controller.myCenter;

import com.fellow.service.PostService;
import com.fellow.web.base.WebAbstract;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wubiao on 2016/11/21.
 */

@Controller
@RequestMapping(MyCenterController.VIEW_PATH)
public class MyCenterController extends WebAbstract<PostService> {
    public static final String VIEW_PATH = "/myCenter";

    @RequestMapping("/index")
    public String index() {
        return VIEW_PATH + "/index";
    }

}
