package com.fellow.web.controller.myCenter;

import com.fellow.service.PostCommentReplyService;
import com.fellow.web.base.WebAbstract;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wubiao on 25/9/2017.
 */

@Controller
@RequestMapping(MyAttitudeInfoController.VIEW_PATH)
public class MyAttitudeInfoController extends WebAbstract<PostCommentReplyService> {
    public static final String VIEW_PATH = "/myCenter/myAttitudeInfo";


    @RequestMapping("/index")
    public String index(Model model) {
        return "/myCenter/myAttitudeInfo/index";
    }
}
