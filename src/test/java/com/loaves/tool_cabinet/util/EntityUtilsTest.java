package com.loaves.tool_cabinet.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EntityUtilsTest {

    @Test
    void test01() {

        List<Integer> testList = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            testList.add(i);
        }

        List<List<Integer>> pageList = EntityUtils.pageList(testList, 23);

        System.out.println(pageList);
    }
}
