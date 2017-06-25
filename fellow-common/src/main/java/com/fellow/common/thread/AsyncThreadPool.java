package com.fellow.common.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wubiao on 2017/1/6.
 */
public class AsyncThreadPool {

    private static ExecutorService asyncThreadPool = Executors.newFixedThreadPool(2);

    public static void addTask(Runnable runnable){
        asyncThreadPool.execute(runnable);
    }


}
