package com.loaves.tool_cabinet.pattern.template;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述：
 *
 * @author LBF
 * @date 2021/11/11 13:52
 **/
@Slf4j
public class GetUpEarly extends GoWork {
    @Override
    public void getUp() {
        log.info("7:00起床，时间还早.");
    }

    @Override
    public void transport() {
        log.info("步行去上班.");
    }
}
