package com.loaves.tool_cabinet.pattern.template;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class GoCinemaTest {
    @Test
    void test01() {
        GoCinema goCinema = new GoCinema();
        goCinema.goByNet("黄牛", "100");

    }
}
