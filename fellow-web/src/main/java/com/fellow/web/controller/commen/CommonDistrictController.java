package com.fellow.web.controller.commen;

import com.fellow.domain.model.CommonDistrict;
import com.fellow.domain.query.CommonDistrictQuery;
import com.fellow.domain.web.Response;
import com.fellow.service.CommonDistrictService;
import com.fellow.web.base.WebAbstract;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wubiao on 2017/2/23.
 */
@Controller
@RequestMapping(CommonDistrictController.VIEW_PATH)
public class CommonDistrictController extends WebAbstract<CommonDistrictService> {
    public static final String VIEW_PATH = "/common/district";

    @RequestMapping(value = "/selectByUpid")
    @ResponseBody
    public Response selectByUpid(CommonDistrictQuery commonDistrictQuery) {
        Response response = Response.newResponse();
        List<CommonDistrict> commonDistrictList = service.selectByUpid(commonDistrictQuery);
        response.setBody(commonDistrictList);
        response.setSuccess(true);
        return response;
    }

}
