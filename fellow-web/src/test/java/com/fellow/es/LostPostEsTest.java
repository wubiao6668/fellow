package com.fellow.es;

import com.fellow.MainTest;
import com.fellow.domain.es.LostPostEsDomain;
import com.fellow.service.es.LostPostEsService;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by wubiao on 30/9/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:elsaticsearch.xml"})
public class LostPostEsTest{
    @Resource
    private LostPostEsRepository lostPostEsRepository;

    @Test
    public void saveTest(){
        LostPostEsDomain lostPostEsDomain = new LostPostEsDomain();
        lostPostEsDomain.setId(System.currentTimeMillis());
        lostPostEsDomain.setTitle(RandomStringUtils.randomAlphabetic(4) + "-寒潮预警！21款儿童秋衣秋裤评测，宝妈速来~");
        lostPostEsDomain.setPostText("万水千山总是情，没穿秋裤行不行？不行！秋风瑟瑟，又到了你妈觉得你冷的季节。响应宝爸宝妈的号召，宝宝干妈组又一力作——儿童秋衣秋裤评测，正式出炉！评测前，我们征求了放心选宝宝成长群里各位爹妈的意见，选择了21款适合4-5岁小朋友（规格为120cm左右）的秋衣秋裤进行评测。");
        lostPostEsRepository.save(lostPostEsDomain);
    }


}
