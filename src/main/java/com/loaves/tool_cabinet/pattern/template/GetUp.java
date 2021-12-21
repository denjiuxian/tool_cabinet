package com.loaves.tool_cabinet.pattern.template;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述：正常起床
 *
 * @author LBF
 * @date 2021/11/11 13:54
 **/
@Slf4j
public class GetUp extends GoWork {
    @Override
    public void getUp() {
        log.info("8:00起床，正常时间。");
    }

    @Override
    public void transport() {
        log.info("骑自行车上班。");
    }
}
