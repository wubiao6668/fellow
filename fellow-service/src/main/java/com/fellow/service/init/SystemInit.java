package com.fellow.service.init;

import com.fellow.service.es.IndexInitEsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;

/**
 * Created by wubiao on 30/9/2017.
 */
public class SystemInit {
    public Log log = LogFactory.getLog(this.getClass());
    @Resource
    private IndexInitEsService esIndexInitService;

    public void init(){
        log.info("##################                init begin            ##########################");
//        esIndexInitService.createIndexIfNotExists();
        log.info("##################                init end            ##########################");
    }
}
