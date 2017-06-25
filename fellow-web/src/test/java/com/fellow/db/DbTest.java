package com.fellow.db;

import com.fellow.MainTest;
import com.fellow.domain.query.LostPostQuery;
import com.fellow.service.LostPostService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Created by wubiao on 2016/9/11.
 */
public class DbTest extends MainTest {

    @Autowired
    private LostPostService lostPostService;

    @Test
    public void testPostService(){
        lostPostService.findList(new LostPostQuery());

    }

}
