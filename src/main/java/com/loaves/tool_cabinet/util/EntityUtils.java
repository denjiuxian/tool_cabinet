package com.loaves.tool_cabinet.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：对象操作工具类
 *
 * @author LBF
 * @date 2021/11/3 11:39
 **/
public class EntityUtils {

    /**
     * list 集合分页,把一个长的list按照pageSize长度拆分成若干个短List
     *
     * @param list     待分页集合
     * @param pageSize 分页数
     * @param <T>      对象类型
     * @return 分页后数据
     */
    public static <T> List<List<T>> pageList(List<T> list, int pageSize) {
        //空的集合返回空数据
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }

        //用于接收分页后的返回值
        List<List<T>> retPageList = new ArrayList<>();
        //计算分页开始和分页结束位置
        int pageTot = Math.min(pageSize, list.size());
        int pageFrom = 0;
        //循环分页截取
        do {
            retPageList.add(list.subList(pageFrom, pageTot));
            pageFrom = pageTot;
            pageTot = Math.min(pageTot + pageSize, list.size());
        } while (pageTot != pageFrom);

        return retPageList;
    }
}
