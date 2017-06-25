/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.fellow.web.controller;

import com.fellow.service.UserService;
import com.fellow.web.base.WebAbstract;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by wubiao on 2016/8/9.
 */
@Controller
@RequestMapping("")
public class ControllerIndex extends WebAbstract<UserService> {
    private Log log = LogFactory.getLog(this.getClass());
    @Resource
    private UserService userService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/selectSchool")
    public String selectSchool(){
        return "/common/selectSchool";
    }

    @RequestMapping("/selectMajor")
    public String selectMajor(){
        return "/common/selectMajor";
    }



}
