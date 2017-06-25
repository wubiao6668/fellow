package com.fellow.jsoup;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.owasp.html.PolicyFactory;
import org.owasp.html.examples.EbayPolicyExample;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wubiao on 2016/9/6.
 */
public class JsoupTest {

    @Test
    public void testXss() {
        long startTime = System.currentTimeMillis();
//        for (int i = 0 ; i < 10000;i++) {
        Whitelist whitelist = Whitelist.relaxed();
        whitelist.addAttributes("img", "src", "src", "width").addProtocols("img", "src", new String[]{"http", "https", "/"});
        String untrust = "12<div>sdf''''''\"sdfs</div></script>alert(1)</script><img alt=\"/头像\" src=\"resource/img/user.png\" class=\"nav-user-photo\"><img src='http://dd.jpg'>";
        untrust = untrust.replaceAll("</script", "&lt/script");
        untrust = untrust.replaceAll("<script", "&ltscript");
        System.out.println(Jsoup.clean(untrust, whitelist));
        System.out.println(Jsoup.clean("12<script>alert(1)</script><img alt=\"头像\" src=\"/resource/img/user.png\" class=\"nav-user-photo\"><img src='dd.jpg'>", Whitelist.none()));
//        }
        System.out.println(System.currentTimeMillis() - startTime);
    }

    @Test
    public void testOwasp() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("ddd", "'''");
//        String mapJson = JSONUtils.toJSONString(map);
//        System.out.print(JSON.parseObject(mapJson, map.getClass()));
        String untrustedHTML = "{sdfsd:''}</script>alert(1)sdf\"''dfs12</script>alert(1)<img onclick='javascript:;' alt=\"头像\" src=\"/resource/img/user.png\" class=\"nav-user-photo\"><img src='dd.jpg'>";
        untrustedHTML = untrustedHTML.replaceAll("</script", "&ampgl;/script");
        PolicyFactory policy = EbayPolicyExample.POLICY_DEFINITION;

//        PolicyFactory policy = new HtmlPolicyBuilder()
//                .allowAttributes("src").onElements("img")
//                .allowAttributes("href").onElements("a")
//                .allowStandardUrlProtocols()
//                .allowElements(
//                        "a", "img"
//                ).toFactory();

        String trustedHtml = policy.sanitize(untrustedHTML);
        System.out.println(trustedHtml);
    }

    @Test
    public void testEconding() {
        System.err.println(StringEscapeUtils.escapeHtml("<dsdsds\"''''''''<script>alert(12)</script>"));
    }

    @Test
    public void testParseHtml() {
        String html = "sdfsfsfsf<span>sdfsdfs</span> <div>sdfsdfsdfsdfs</div>sfsfsdfsfs<img root='' relative='' class=\"emoji-img\" src=\"/image/201609/1473227756807Wneh.jpg\"><img root='/' relative='zhangad.jd' class=\"emoji-img\" src=\"/image/201609/1473227756807Wneh.jpg\"><img class=\"emoji-img\" src=\"/image/201609/1473227756807Wneh.jpg\">";
        Document document = Jsoup.parse(html);
        Elements elements = document.select("img[src^='/image/']");
        if (null != elements && elements.size() > 0){
            for (int i = 0 ; i < elements.size() ; i++){
                Element element = elements.get(i);
                String oldUrl = element.attr("src");
                element.attr("src","${name}" + i);
                System.out.println(oldUrl);
            }
        }
        System.out.println(document.body());
    }


    @Test
    public void testHtml(){
        System.out.println(StringEscapeUtils.escapeHtml("'/?<>$!{}[]"));
    }


}
