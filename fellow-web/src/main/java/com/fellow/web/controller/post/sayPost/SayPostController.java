package com.fellow.web.controller.post.sayPost;

import com.fellow.domain.enums.PostPlaceTypeEnum;
import com.fellow.domain.enums.SayPostTypeEnum;
import com.fellow.service.LostPostService;
import com.fellow.web.base.PostAbstractWeb;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wubiao on 2017/1/18.
 */
@Controller
@RequestMapping(SayPostController.VIEW_PATH)
public class SayPostController extends PostAbstractWeb<LostPostService> {
    public static final String VIEW_PATH = "/post/say/post";

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("sayPostTypeEnums", SayPostTypeEnum.values());
        model.addAttribute("postPlaceTypeEnums", PostPlaceTypeEnum.values());
        return getModule() + "/index";
    }


}
