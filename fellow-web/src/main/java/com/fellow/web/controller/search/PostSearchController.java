package com.fellow.web.controller.search;

import com.fellow.service.LostPostService;
import com.fellow.web.base.WebAbstract;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wubiao on 8/10/2017.
 */
@Controller
@RequestMapping(PostSearchController.VIEW_PATH)
public class PostSearchController extends WebAbstract<LostPostService> {
    public static final String VIEW_PATH = "/search/lostPost";

    @RequestMapping("/index")
    public String index() {
        return "/search/lostPost/index";
    }
}
