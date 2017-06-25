package com.fellow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:root/spring-context-root.xml"})
public class MainTest {

    @Test
    public void test(){
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("┌───┼┤┌┬┐┼─┐　┐┌┬─┐╭　╭╯　　╭┐　┌　┐\n" +
                "├─┐　│　　│　│　│┌┐└│　│┼┬│　　┐│　　│　│\n" +
                "│　│　│　　│　│　│　├　│　│││└──┘│　　│　│\n" +
                "│　│　││　│　│　│┌╯　│　│││┌──┐│　　│　│\n" +
                "│　│　├┘　│　│　│　├　│　││││　　│╯　└┼─╯\n" +
                "╯└╯└╰┘└┴╯╯─╯　┘└╯└┘╰┴└──╯└╯　╰─┘");
        System.out.println("");
        System.out.println("");
        System.out.println("");
    }

}
