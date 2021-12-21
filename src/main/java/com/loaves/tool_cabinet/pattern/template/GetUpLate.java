package com.loaves.tool_cabinet.pattern.template;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述：晚起上班
 *
 * @author LBF
 * @date 2021/11/11 13:58
 **/
@Slf4j
public class GetUpLate extends GoWork {
    @Override
    public void getUp() {
        log.info("9:00起床，已经迟到了.");
    }

    @Override
    public void transport() {
        log.info("打车上班.");
    }
}
