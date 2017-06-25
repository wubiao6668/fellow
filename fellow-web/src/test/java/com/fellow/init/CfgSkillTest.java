package com.fellow.init;

import com.fellow.MainTest;
import com.fellow.service.CfgSkillService;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Created by wubiao on 2017/2/24.
 */

public class CfgSkillTest extends MainTest {

    @Resource
    private CfgSkillService cfgSkillService;

    @Test
    public void skillTest() {

        Map<String, String> skillMap = new LinkedHashMap();

        String yuyan = " 普通话" +
                " 粤语" +
                " 英语" +
                " 日语" +
                " 德语" +
                " 手语" +
                " 法语" +
                " 韩语" +
                " 俄语" +
                " 各种外语";
        skillMap.put("语言", yuyan);
        String shiyong = " 开车" +
                " 做饭" +
                " 摄影" +
                " 按摩" +
                " 开网店" +
                " 做生意" +
                " 炒股" +
                " 减肥" +
                " 旅游" +
                " 瑜伽" +
                " 推拿" +
                " 手工制作" +
                " 服装搭配" +
                " 投资理财";
        skillMap.put("实用", shiyong);
        String xinxi = " PS照片" +
                " 网站开发" +
                " 求职简历" +
                " 计算机编程" +
                " 网页制作" +
                " 装电脑系统" +
                " 办公软件";
        skillMap.put("信息", xinxi);
        String tiyu = "游泳" +
                " 围棋" +
                " 象棋" +
                " 网球" +
                " 足球" +
                " 台球" +
                " 羽毛球" +
                " 篮球" +
                " 野外生存" +
                " 跑步" +
                " 跆拳道" +
                " 保龄球" +
                " 高尔夫" +
                " 武术" +
                " 攀岩" +
                " 爬山" +
                " 滑板" +
                " 乒乓球" +
                " 滑雪";
        skillMap.put("体育", tiyu);
        String caiyi = "唱歌" +
                " 钢琴" +
                " 萨克斯" +
                " 书法" +
                " 笛子" +
                " 大提琴" +
                " 吉他" +
                " 街舞" +
                " 爵士舞" +
                " 绘画" +
                " 写作" +
                " 音乐剧" +
                " 肚皮舞" +
                " 茶艺" +
                " 拉丁" +
                " 电吉他" +
                " 钢管舞" +
                " 快板";
        skillMap.put("才艺", caiyi);
        String zhuye = " 心理学" +
                " 销售技巧" +
                " 企业管理" +
                " 会计经验" +
                " 国际贸易" +
                " 中医" +
                " 法律" +
                " 设计学" +
                " 建筑学" +
                " 电力工程";
        skillMap.put("专业", zhuye);
        String gexing = "打发时间" +
                " 淘宝贝" +
                " 观人眉宇" +
                " 星座分析" +
                " 解梦" +
                " 讲笑话" +
                " 聊天" +
                " 打麻将" +
                " 斗地主" +
                " 三国杀" +
                " 手机刷机";
        skillMap.put("个性", gexing);
        Map<String, TreeSet<String>> insertMap = new LinkedHashMap();
        TreeSet<String> skillNameSet = null;
        for (String keySet : skillMap.keySet()) {
            System.out.println("********************          " + keySet + "         ***********************");
            String valueStrs[] = skillMap.get(keySet).split(" ");
            skillNameSet = new TreeSet<String>();
            for (String skillName : valueStrs) {
                if (StringUtils.isNotBlank(skillName)) {
                    skillNameSet.add(skillName);
                }
            }
            insertMap.put(keySet, skillNameSet);
        }
        cfgSkillService.initSkill(insertMap);
    }

}
