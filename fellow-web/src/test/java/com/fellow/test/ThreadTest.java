package com.fellow.test;

import org.junit.Test;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;

/**
 * Created by wubiao on 2016/11/22.
 */
//public class ThreadTest {

public   class ThreadTest {
//    public static void main (String[] args) {
//        Object x = new Vector().elements();
//        System.out.print((x instanceof Enumeration)+",");
//        System.out.print((x instanceof Iterator)+",");
//        System.out.print(x instanceof ListIterator);
//    }

    @Test
    public void testStrLength(){
        System.out.print("115".compareToIgnoreCase("12"));
    }


    public final int value = 4;
    public void doIt()
    {
        int value = 6;
        Runnable r = new Runnable(){
            public final int value = 5;
            public void run(){
                int value = 10;
                System.out.println(this.value);
            }
        };
        r.run(); }
    public static void main(String...args)
    {
        ThreadTest m = new ThreadTest();
        m.doIt(); }

}
