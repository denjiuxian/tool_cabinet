package com.loaves.tool_cabinet.pattern.template;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述：例子介绍两种模板方法的实现-- 传统模式。
 * 场景描述：此处已起床上班为例子。
 * 上班分三步：1-起床（早起，正常起，晚起），2-选择上班的方式（步行，骑车，打车...），3-上班打卡
 * 由于起床方式的不同，上班方式也不同但是最后打开上班方式一样
 *
 * @author LBF
 * @date 2021/11/11 13:41
 **/
@Slf4j
public abstract class GoWork {

    /**
     * 起床：早起，正常起，晚起
     */
    public abstract void getUp();

    /**
     * 选择交通方式
     */
    public abstract void transport();

    public void punchIn() {
        log.info("取出手机，打个卡。开始上班...");
    }

    /**
     * 上班执行方法
     */
    public void work() {
        getUp();
        transport();
        punchIn();
    }
}
