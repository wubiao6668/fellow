package com.fellow.web.controller.personalHome;

import com.fellow.domain.constant.SystemConstant;
import com.fellow.common.exception.BusinessException;
import com.fellow.domain.enums.*;
import com.fellow.domain.model.User;
import com.fellow.domain.model.UserDetail;
import com.fellow.domain.query.UserQuery;
import com.fellow.domain.web.Response;
import com.fellow.service.UserService;
import com.fellow.service.cache.CityCacheService;
import com.fellow.service.cache.CollegeSchoolCacheService;
import com.fellow.service.cache.MajorCacheService;
import com.fellow.web.base.WebAbstract;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by wubiao on 2016/9/27.
 */
@Controller
@RequestMapping(PersonalBaseInfoController.VIEW_PATH)
public class PersonalBaseInfoController extends WebAbstract<UserService> {
    public static final String VIEW_PATH = "/personal/home/baseInfo";

    @Resource
    private CityCacheService cityCacheService;
    @Resource
    private MajorCacheService majorCacheService;
    @Resource
    private CollegeSchoolCacheService collegeSchoolCacheService;

    @RequestMapping(value = {"/index"})
    public String baseHome(Model model) {
        UserQuery userQuery = new UserQuery();
        userQuery.setAccount(super.getAccount());
        User user = service.selectBaseInfo(userQuery);
        UserDetail userDetail = user.getUserDetail();
        userDetail.setSexText(SexEnum.getValueByKey(userDetail.getSex()));
        userDetail.setEducationText(EducationEnum.getEdeValueByKey(userDetail.getEducation()));
        userDetail.setProfessionText(ProfessionEnum.getValueByKey(userDetail.getProfession()));
        userDetail.setRelationshipStatusText(RelationshipStatusEnum.getValueByKey(userDetail.getRelationshipStatus()));
        if (StringUtils.isNotBlank(userDetail.getDomicilePath())) {
            String domicilePath = userDetail.getDomicilePath();
            String[] domicilePaths = domicilePath.split(SystemConstant.CONNECT_SYMBOL);
            if (domicilePaths.length >= 2 && StringUtils.isNotBlank(domicilePaths[1])) {
                userDetail.setDomicileFirst(Long.parseLong(domicilePaths[0]));
                userDetail.setDomicileFirstText(cityCacheService.getNameById(Long.parseLong(domicilePaths[0])));
                userDetail.setDomicileSecond(Long.parseLong(domicilePaths[1]));
                userDetail.setDomicileSecondText(cityCacheService.getNameById(Long.parseLong(domicilePaths[1])));
            } else {
                userDetail.setDomicileFirst(Long.parseLong(domicilePaths[0]));
                userDetail.setDomicileFirstText(cityCacheService.getNameById(Long.parseLong(domicilePaths[0])));
            }
        }
        if (StringUtils.isNotBlank(userDetail.getHometownPath())) {
            String hometownPath = userDetail.getHometownPath();
            String[] hometownPaths = hometownPath.split(SystemConstant.CONNECT_SYMBOL);
            if (hometownPaths.length >= 2 && StringUtils.isNotBlank(hometownPaths[1])) {
                userDetail.setHometownFirst(Long.parseLong(hometownPaths[0]));
                userDetail.setHometownFirstText(cityCacheService.getNameById(Long.parseLong(hometownPaths[0])));
                userDetail.setHometownSecond(Long.parseLong(hometownPaths[1]));
                userDetail.setHometownSecondText(cityCacheService.getNameById(Long.parseLong(hometownPaths[1])));
            } else {
                userDetail.setHometownFirst(Long.parseLong(hometownPaths[0]));
                userDetail.setHometownFirstText(cityCacheService.getNameById(Long.parseLong(hometownPaths[0])));
            }
        }
        String majorPath = userDetail.getMajorPath();
        if (StringUtils.isNotBlank(majorPath)) {
            String[] majorPaths = majorPath.split(SystemConstant.CONNECT_SYMBOL);
            userDetail.setMajorFirst(Long.parseLong(majorPaths[0]));
        }
        String collegeSchoolPath = userDetail.getCollegeSchoolPath();
        if (StringUtils.isNotBlank(collegeSchoolPath)) {
            String[] collegeSchoolPaths = collegeSchoolPath.split(SystemConstant.CONNECT_SYMBOL);
            userDetail.setCollegeSchoolFirst(Long.parseLong(collegeSchoolPaths[0]));
        }
        userDetail.setMajorText(majorCacheService.getMajorNameById(userDetail.getMajorId()));
        userDetail.setCollegeSchoolIdText(collegeSchoolCacheService.getSchoolNameById(userDetail.getCollegeSchoolId()));
        HashMap<Integer, String> firstCityMap = new HashMap<Integer, String>();
        firstCityMap.put(10, "辽宁");
        firstCityMap.put(20, "海南");
        HashMap<Integer, String> secondeCityMap = new HashMap<Integer, String>();
        secondeCityMap.put(30, "沈阳");
        secondeCityMap.put(40, "海口");
        userDetail.setMonthlyIncomeText(MonthlyIncomeEnum.getValueByKey(userDetail.getMonthlyIncome()));
        model.addAttribute("user", user);
        model.addAttribute("userDetail", userDetail);
        model.addAttribute("firstCityMap", firstCityMap);
        model.addAttribute("secondeCityMap", secondeCityMap);
        model.addAttribute("sexEnumMap", SexEnum.getEnumMap());
        model.addAttribute("professionEnums", ProfessionEnum.values());
        model.addAttribute("relationshipStatusEnums", RelationshipStatusEnum.values());
        model.addAttribute("monthlyIncomeEnums", MonthlyIncomeEnum.values());
        model.addAttribute("educationEnumMap", EducationEnum.getEnumMap());
        return VIEW_PATH + "/index";
    }

    @RequestMapping("/updateBaseInfo")
    @ResponseBody
    public Response updateBaseInfo(User user) {
        Response response = Response.newResponse();
        try {
            user.setAccount(super.getAccount());
            user.setUpdateAccount(super.getAccount());
            user.setUpdateName(super.getUserName());
            UserDetail userDetail = user.getUserDetail();
            StringBuffer pathBf = new StringBuffer();
            if (null != userDetail.getDomicileFirst()) {
                pathBf.append(userDetail.getDomicileFirst() + SystemConstant.CONNECT_SYMBOL);
                if (null != userDetail.getDomicileSecond()) {
                    pathBf.append(userDetail.getDomicileSecond() + SystemConstant.CONNECT_SYMBOL);
                }
            }
            userDetail.setDomicilePath(pathBf.toString());
            pathBf = new StringBuffer();
            if (null != userDetail.getHometownFirst()) {
                pathBf.append(userDetail.getHometownFirst() + SystemConstant.CONNECT_SYMBOL);
                if (null != userDetail.getHometownSecond()) {
                    pathBf.append(userDetail.getHometownSecond() + SystemConstant.CONNECT_SYMBOL);
                }
            }
            userDetail.setHometownPath(pathBf.toString());
            if (null != userDetail.getMajorFirst()) {
                userDetail.setMajorPath(userDetail.getMajorFirst().toString() + SystemConstant.CONNECT_SYMBOL);
            }
            if (null != userDetail.getCollegeSchoolFirst()) {
                userDetail.setCollegeSchoolPath(userDetail.getCollegeSchoolFirst() + SystemConstant.CONNECT_SYMBOL);
            }else {
                userDetail.setCollegeSchoolPath("");
            }
            userDetail.setAccount(super.getAccount());
            userDetail.setUpdateAccount(super.getAccount());
            userDetail.setUpdateName(super.getUserName());
            service.updateBaseInfo(user);
            response.setSuccess(true);
        } catch (BusinessException e) {
            response.setMessage(e.getMessage());
            log.error("updateBaseInfo--修改个人信息失败!--e", e);
        } catch (Exception e) {
            response.setMessage("修改个人信息失败");
            log.error("updateBaseInfo--修改个人信息失败!--e", e);
        }
        return response;
    }


    public static final void main(String[] arg) {
        HashMap<Integer, String> firstCity = new HashMap<Integer, String>();
        firstCity.put(10, "辽宁");
        System.out.println(firstCity.keySet());
    }
}
