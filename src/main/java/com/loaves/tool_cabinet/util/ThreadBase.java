package com.loaves.tool_cabinet.util;

import lombok.Data;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * 描述：多线程基础类，继承子类必须重写call()方法
 * <F> : 线程处理的原数据
 * <T> : 线程处理返回结果
 *
 * @author LBF
 * @date 2021/11/5 16:44
 **/
@Data
public class ThreadBase<F, T> implements Callable<T> {
    /**
     * 待处理的数据
     */
    private List<F> delList;

    /**
     * 线程执行分组编号
     */
    private int no;


    @Override
    public T call() throws Exception {
        return null;
    }
}
