package com.loaves.tool_cabinet.pattern.template;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 描述：例子介绍两种模板方法的实现--java 8 简化模式。
 * 场景描述：此处已去电影院看电影为例
 * 办理业务分三步：1-买票（网上订票，现场买票，黄牛票），2-看电影（喜剧片，科幻片，动作片），3-看完评价
 * 买票方式不同，看的影片不同，最后评价操作相同
 * <p>
 * https://developer.aliyun.com/article/787932?spm=a2c6h.12873581.0.darticle787932.1dc8298azs05bz&groupcode=javaup
 *
 * @author LBF
 * @date 2021/11/11 13:41
 **/
@Slf4j
public class GoCinema {

    private void execute(Supplier<String> supplier, Consumer<String> consumer) {

        String byType = supplier.get();
        System.out.println("购票方式=》" + byType);
        if ("网上订票".equals(byType)) {
            System.out.println("输入中...选票成功...出票完成。");
        } else if ("网上订票".equals(byType)) {
            System.out.println("查询剩余票数...选择场次...选择支付...取票完成。");
        } else {
            System.out.println("非正常途径买票。");
        }

        consumer.accept("测试A");

        judge();
    }


    private void judge() {
        System.out.println("give a praised");
    }

    public void goByNet(String type, String delWay) {
        Supplier<String> typeSup = () -> "" + type;
        Consumer<String> consumer = a -> System.out.println("save " + delWay +a);
        execute(typeSup, consumer);

    }

}