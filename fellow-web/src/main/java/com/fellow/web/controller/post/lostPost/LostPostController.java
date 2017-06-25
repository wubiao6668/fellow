package com.fellow.web.controller.post.lostPost;

import com.fellow.domain.enums.LostPosGoodTypeEnum;
import com.fellow.domain.enums.PostPeriodEnum;
import com.fellow.domain.enums.PostPlaceTypeEnum;
import com.fellow.domain.enums.SayPostTypeEnum;
import com.fellow.domain.model.LostPost;
import com.fellow.domain.query.LostPostQuery;
import com.fellow.domain.web.Response;
import com.fellow.service.LostPostDetailService;
import com.fellow.service.LostPostService;
import com.fellow.web.base.PostAbstractWeb;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by wubiao on 2016/9/1.
 */
@Controller
@RequestMapping(LostPostController.VIEW_PATH)
public class LostPostController extends PostAbstractWeb<LostPostService> {
    public static final String VIEW_PATH = "/post/lost/post";

    @Resource
    private LostPostDetailService lostPostDetailService;

    @RequestMapping("/mapIndex")
    public String mapIndex(Model model) {
        model.addAttribute("sayPostTypeEnums", SayPostTypeEnum.values());
        model.addAttribute("postPlaceTypeEnums", PostPlaceTypeEnum.values());
        model.addAttribute("lostPosGoodTypeEnums", LostPosGoodTypeEnum.values());
        model.addAttribute("lostPostPlaceTypeEnums", PostPlaceTypeEnum.values());
        model.addAttribute("postPeriodEnums", PostPeriodEnum.values());
        return VIEW_PATH + "/mapIndex";
    }

    @RequestMapping("/listIndex")
    public String listIndex(Model model) {
        model.addAttribute("sayPostTypeEnums", SayPostTypeEnum.values());
        model.addAttribute("postPlaceTypeEnums", PostPlaceTypeEnum.values());
        model.addAttribute("lostPosGoodTypeEnums", LostPosGoodTypeEnum.values());
        model.addAttribute("lostPostPlaceTypeEnums", PostPlaceTypeEnum.values());
        model.addAttribute("postPeriodEnums", PostPeriodEnum.values());
        return VIEW_PATH + "/listIndex";
    }

    @RequestMapping(value = "queryPostMapList", method = RequestMethod.POST)
    @ResponseBody
    public Response queryPostMapList(LostPostQuery q) {
        return super.queryPostMapList(q,getModule() + "/postMapList.vm");
    }

    @RequestMapping(value = "queryListPost", method = RequestMethod.POST)
    @ResponseBody
    public Response queryListPost(LostPostQuery q) {
        return super.queryListPost(q,getModule() + "/postList.vm");
    }

    @RequestMapping(value = "/paginator", method = RequestMethod.POST)
    @ResponseBody
    public Response paginator(LostPostQuery queryDomain) {
        return super.paginator(queryDomain);
    }


    @RequestMapping("/publish")
    @ResponseBody
    public Response publish(LostPost lostPost) {
        return super.publish(lostPost);
    }

    @RequestMapping("/publishPost")
    public String publishPost(Model model) {
        model.addAttribute("sayPostTypeEnums", SayPostTypeEnum.values());
        model.addAttribute("postPlaceTypeEnums", PostPlaceTypeEnum.values());
        model.addAttribute("postPeriodEnums", PostPeriodEnum.values());
        model.addAttribute("lostPosGoodTypeEnums", LostPosGoodTypeEnum.values());
        model.addAttribute("lostPostPlaceTypeEnums", PostPlaceTypeEnum.values());
        model.addAttribute("postPeriodEnums", PostPeriodEnum.values());
        return VIEW_PATH + "/publish_post";
    }


}
