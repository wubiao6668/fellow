package com.fellow.web.controller;

import com.fellow.domain.enums.FriendTypeEnum;
import com.fellow.domain.model.Friend;
import com.fellow.domain.web.Response;
import com.fellow.service.FollowInfoService;
import com.fellow.service.FriendService;
import com.fellow.web.base.WebAbstract;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by wubiao on 2017/4/14.
 */
@Controller
@RequestMapping(FellowInfoController.VIEW_PATH)
public class FellowInfoController extends WebAbstract<FollowInfoService> {
    public static final String VIEW_PATH = "/fellowInfo";
    @Resource
    private FriendService friendService;

    @RequestMapping(value = "/cancelFollow")
    @ResponseBody
    public Response cancelFollow(Friend friend) {
        Response response = Response.newResponse();
        friendService.cancelMeFollow(friend);
        response.setSuccess(true);
        return response;
    }

    @RequestMapping("/addFollow")
    @ResponseBody
    public Response addFollow(Friend friend) {
        Response response = Response.newResponse();
        friend.setAccount(super.getAccount());
        friend.setUpdateAccount(super.getAccount());
        friend.setUpdateName(super.getUserName());
        friend.setFriendType(FriendTypeEnum.FOLLOW.getKey());
        friendService.addFollow(friend);
        response.setSuccess(true);
        return response;
    }





}
