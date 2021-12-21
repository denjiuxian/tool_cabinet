package com.loaves.tool_cabinet.util.demo;


import com.loaves.tool_cabinet.util.StringUtils;
import com.loaves.tool_cabinet.util.ThreadBase;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 *
 * @author LBF
 * @date 2021/11/3 10:33
 **/
@Slf4j
public class ThreadByCallable extends ThreadBase<String, List<String>> {

    @Override
    public List<String> call() {
        List<String> retList = new ArrayList<>();

        for (String s : this.getDelList()) {
            String st = "[" + s + "]" + StringUtils.createSixDigitRandom();
            //多线程实现：生成6位随机字符串
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

            }
            log.info("当前执行线程{}，运行结果{}", getNo(),st);
            retList.add(st);
        }
        return retList;
    }

}
