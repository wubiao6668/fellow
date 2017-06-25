package com.fellow.web.controller.personalHome;

import com.fellow.domain.enums.EducationEnum;
import com.fellow.domain.enums.SexEnum;
import com.fellow.service.UserService;
import com.fellow.web.base.WebAbstract;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wubiao on 2016/9/27.
 */
@Controller
@RequestMapping(PersonalHomeController.VIEW_PATH)
public class PersonalHomeController extends WebAbstract<UserService> {
    public static final String VIEW_PATH = "/personal/home";

    @RequestMapping(value = {"/index"})
    public String baseIndex(Model model) {
        model.addAttribute("sexEnumMap", SexEnum.getEnumMap());
        model.addAttribute("educationEnumMap", EducationEnum.getEnumMap());
        return VIEW_PATH + "/index";
    }

}
