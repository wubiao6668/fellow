package com.fellow.web.controller;

import com.fellow.common.constant.SystemConstant;
import com.fellow.common.util.PinyinHelperUtil;
import com.fellow.domain.enums.FriendTypeEnum;
import com.fellow.domain.enums.RestrictDynamicsEnum;
import com.fellow.domain.enums.SexEnum;
import com.fellow.domain.enums.ShowDynamicsEnum;
import com.fellow.domain.model.Friend;
import com.fellow.domain.query.FriendQuery;
import com.fellow.domain.vo.FriendMeFellowVo;
import com.fellow.domain.web.Response;
import com.fellow.service.FriendService;
import com.fellow.web.base.WebAbstract;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by wubiao on 2016/11/14.
 */

@Controller
@RequestMapping(BlackManagerController.VIEW_PATH)
public class BlackManagerController extends WebAbstract<FriendService> {
    public static final String VIEW_PATH = "/friend/blackListManager";

    @RequestMapping(value = "/index")
    public String index(Model model) {
        FriendQuery friendQuery = new FriendQuery();
        loadMeFellow(friendQuery, model);
        return VIEW_PATH + "/index";
    }

    @RequestMapping("/loadMoreFriend")
    public String loadMoreFriend(FriendQuery friendQuery, Model model) {
        loadMeFellow(friendQuery, model);
        return VIEW_PATH + "/load_more";
    }

    private void loadMeFellow(FriendQuery friendQuery, Model model) {
        friendQuery.setPageSize(1);
        friendQuery.setFriendType(FriendTypeEnum.BLACK_LIST.getKey());
        friendQuery.setAccount(super.getAccount());
        friendQuery.initMysqlLimit();
        List<FriendMeFellowVo> friendList = service.selectPersonalFriend(friendQuery);
        long friendCount =service.selectPersonalFriendCount(friendQuery);
        if (CollectionUtils.isNotEmpty(friendList)) {
            for (FriendMeFellowVo friendTemp : friendList) {
                //计算年龄
                if (null != friendTemp.getBirthday()){
                    friendTemp.setAge(Integer.parseInt(DateFormatUtils.format(new Date(),"yyyy")) - Integer.parseInt(DateFormatUtils.format(friendTemp.getBirthday(),"yyyy")));
                }
            }
            if (friendList.size() >= friendQuery.getPageSize()) {
                model.addAttribute("showMore", true);
            }
        }
        model.addAttribute("RestrictDynamicsEnumAllow", RestrictDynamicsEnum.ALLOW.getKey());
        model.addAttribute("ShowDynamicsEnumLook", ShowDynamicsEnum.LOOK.getKey());
        model.addAttribute("female_sex", SexEnum.FEMALE.getKey());
        model.addAttribute("page", friendQuery.getPage() + 1);
        model.addAttribute("pageSize", friendQuery.getPageSize());
        model.addAttribute("friendList", friendList);
        model.addAttribute("friendCount", friendCount);
    }

    @RequestMapping("/updateFriendAlias")
    @ResponseBody
    public Response updateFriendAlias(Friend friend) {
        Response response = Response.newResponse();
        if (StringUtils.isBlank(friend.getFriendAlias())) {
            friend.setFriendAlias("");
        }
        friend.setAccount(super.getAccount());
        friend.setUpdateAccount(super.getAccount());
        friend.setUpdateName(super.getUserName());
        service.updateFriendAlias(friend);
        response.setSuccess(true);
        return response;
    }

    @RequestMapping("/removeToBlackList")
    @ResponseBody
    public Response removeToBlackList(Friend friend) {
        Response response = Response.newResponse();
        friend.setAccount(super.getAccount());
        friend.setUpdateAccount(super.getAccount());
        friend.setUpdateName(super.getUserName());
        service.removeToBlackList(friend);
        response.setSuccess(true);
        return response;
    }

    @RequestMapping("/delFromBlackList")
    @ResponseBody
    public Response delFromBlackList(Friend friend) {
        Response response = Response.newResponse();
        friend.setAccount(super.getAccount());
        friend.setUpdateAccount(super.getAccount());
        friend.setUpdateName(super.getUserName());
        friend.setFriendType(FriendTypeEnum.STRANGER.getKey());
        service.updateFriendType(friend);
        response.setSuccess(true);
        return response;
    }


}




























