package com.fellow.web.controller.commen;

import com.fellow.domain.model.Major;
import com.fellow.domain.query.MajorQuery;
import com.fellow.domain.web.Response;
import com.fellow.service.MajorService;
import com.fellow.web.base.WebAbstract;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wubiao on 2017/2/23.
 */
@Controller
@RequestMapping(MajorController.VIEW_PATH)
public class MajorController extends WebAbstract<MajorService> {
    public static final String VIEW_PATH = "/common/major";

    @RequestMapping(value = "/selectMajorByUpid")
    @ResponseBody
    public Response selectSkillByUpid(MajorQuery majorQuery) {
        Response response = Response.newResponse();
        List<Major> majorList = service.selectMajorByUpid(majorQuery);
        response.setBody(majorList);
        response.setSuccess(true);
        return response;
    }

}
