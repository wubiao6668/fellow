package com.fellow.es;

import com.fellow.MainTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by wubiao on 29/9/2017.
 */
public class EsTest extends MainTest {

    @Resource
    private TestService testService;

    @Test
    public void test01() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            testService.test();
        }
        System.out.println(System.currentTimeMillis()-startTime);
    }


}
