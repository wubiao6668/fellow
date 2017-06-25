package com.fellow.list;

import com.alibaba.fastjson.JSON;
import com.fellow.common.emotion.EmoticonUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wubiao on 2016/9/9.
 */
public class ListTest {


    @Test
    public void testList() {

        List<Integer> idList = new ArrayList<Integer>();
        idList.add(1);
        idList.add(2);
        idList.add(3);
        idList.add(4);
        idList.add(5);
//        for (int i = 0; i < idList.size(); i++) {
////            System.out.print(JSON.toJSON(idList));
//            idList.remove(i);
////            System.out.print(JSON.toJSON(idList));
//
//        }
        for (Integer ddd : idList) {
            idList.remove(1);
        }
        System.out.print(JSON.toJSON(idList));
    }
    @Test
    public void testMap(){
        Double dkey1 = 9898888888888.4444444444444444444444444444444444444d;
        Double dkey2 = 989888098888.44444444444449347734953453453493439343D;
        Map<String,String> map = new HashMap<String, String>();
        map.put(dkey1.toString()+"_" +dkey2.toString(),"sdfsdfs");
        map.put(dkey1.toString()+"_1" +dkey2.toString(),"sdfsdfs");
        map.put(dkey1.toString()+"_2" +dkey2.toString(),"sdfsdfs");
        map.put(dkey1.toString()+"_3" +dkey2.toString(),"sdfsdfs");
        System.out.println(JSON.toJSONString(map));
    }

    @Test
    public void testMaps(){
        Map<String,Double[]> key = new HashMap<String, Double[]>();
        key.put("dd",new Double[]{1d,2d});
        System.out.println(JSON.toJSONString(key));
    }

    @Test
    public void testTime(){
        for (int  i = 0 ; i < 10;i++){
            System.out.println(System.currentTimeMillis());
        }
    }

    @Test
    public void testIndex(){
        String nnvar  = "http://wdfasdf.com/sdfs//d.jsp";
        int fromIndex = nnvar.indexOf("//") +2;
        int beginIndex = nnvar.indexOf("/",fromIndex);
        System.out.println(nnvar.substring(0,beginIndex));
        System.out.println(nnvar.substring(beginIndex+1));
    }

    @Test
    public void testEmo(){
//        List<EmoticonItem> emotionItems = new ArrayList<EmoticonItem>();
//        JSON.parseObject(EmoticonUtil.emotionStr,new ArrayList<EmoticonType>().getClass());
//        JSON.parseObject(EmoticonUtil.emotionStr,new TypeReference<EmoticonType[]>(){}.getClass());
//        ArrayList<EmoticonType> list2 = JSON.parseObject(EmoticonUtil.emotionStr, new TypeReference<ArrayList<EmoticonType>>(){});
        System.out.println(EmoticonUtil.emoticonMap);
    }

}
