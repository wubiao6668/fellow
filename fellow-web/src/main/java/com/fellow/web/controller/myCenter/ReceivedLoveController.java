package com.fellow.web.controller.myCenter;

import com.fellow.domain.enums.AttitudeStatusEnum;
import com.fellow.domain.query.AttitudeInfoQuery;
import com.fellow.domain.vo.AttitudeInfoVo;
import com.fellow.service.AttitudeInfoService;
import com.fellow.web.base.WebAbstract;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * Created by wubiao on 17/9/2017.
 */
@Controller
@RequestMapping(ReceivedLoveController.VIEW_PATH)
public class ReceivedLoveController extends WebAbstract<AttitudeInfoService> {
    public static final String VIEW_PATH = "/myCenter/receivedLove";

    @RequestMapping("/index")
    public String index(Model model, AttitudeInfoQuery attitudeInfoQuery) {
        condition(model, attitudeInfoQuery);
        return "/myCenter/receivedLove/index";
    }

    @RequestMapping("/more")
    public String more(Model model, AttitudeInfoQuery attitudeInfoQuery) {
        condition(model, attitudeInfoQuery);
        return "/myCenter/receivedLove/more";
    }

    private void condition(Model model, AttitudeInfoQuery attitudeInfoQuery) {
        attitudeInfoQuery.initMysqlLimit();
        attitudeInfoQuery.setToAccount(super.getAccount());
        attitudeInfoQuery.setStatus(AttitudeStatusEnum.LOVE.getKey());
        attitudeInfoQuery.setSortColumns(" attitude_time desc");
        if (0 >= attitudeInfoQuery.getQueryTime()) {
            attitudeInfoQuery.setAttitudeTimeEnd(new Date());
        } else {
            attitudeInfoQuery.setAttitudeTimeEnd(new Date(attitudeInfoQuery.getQueryTime()));
        }
        List<AttitudeInfoVo> attitudeInfoVoList = service.selectByToAccount(attitudeInfoQuery);
        long attitudeEndTime = 0;
        boolean hasNextPage = false;
        if (CollectionUtils.isNotEmpty(attitudeInfoVoList)){
             attitudeEndTime = attitudeInfoVoList.get(attitudeInfoVoList.size() - 1).getAttitudeTime().getTime();
             hasNextPage = attitudeInfoQuery.getPageSize() == attitudeInfoVoList.size();
        }
        model.addAttribute("attitudeInfoVoList", attitudeInfoVoList);
        model.addAttribute("attitudeEndTime", attitudeEndTime);
        model.addAttribute("hasNextPage", hasNextPage);
    }


}






