package com.loaves.tool_cabinet.util;

import com.loaves.tool_cabinet.util.demo.ThreadByCallable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadUtilsTest {
    @Test
    void test03() {
        //线程池chi
        ExecutorService pool = Executors.newFixedThreadPool(6);
        Runnable t = () -> {
            for (int i = 0; i < 1000; i++) {
                System.out.println(Thread.currentThread().getName()
                        + "的i值为:" + i);
            }
        };
        pool.submit(t);
        pool.submit(t);
    }

    @Test
    void test04()  {
        List<String> strList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            strList.add(i + "");
        }
        try {
            List<List<String>> list = ThreadUtils.threadUtilsInit(20, strList).doThreadTask("测试线程", ThreadByCallable.class);
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
