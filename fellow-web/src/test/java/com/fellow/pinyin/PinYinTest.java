package com.fellow.pinyin;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import org.junit.Test;

/**
 * Created by wubiao on 2016/11/14.
 */
public class PinYinTest {

    @Test
    public void testPin(){
        String shouZimu = null;
        try {
            shouZimu = PinyinHelper.getShortPinyin("鹵");
        } catch (PinyinException e) {
            e.printStackTrace();
        }
        System.out.println(shouZimu);

    }


}
