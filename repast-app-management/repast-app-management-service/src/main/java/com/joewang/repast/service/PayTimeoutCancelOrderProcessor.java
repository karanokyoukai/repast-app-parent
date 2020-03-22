package com.joewang.repast.service;


import com.joewang.repast.mapper.OrderMapper;
import com.joewang.repast.model.Order;
import com.joewang.repast.utils.DelayUtils;
import org.springframework.scheduling.annotation.Async;

/**
 * @Author:DongMengKe
 * 超时取消的时间到了，这里要执行对订单的具体取消操作
 * @Date:2019/11/26 002618:39
 * @Version 1.0
 */

public class PayTimeoutCancelOrderProcessor implements Runnable {
    private Long orderid;
    public PayTimeoutCancelOrderProcessor(Long orderid){
        this.orderid = orderid;
    }
    @Override
    @Async
    public void run(){
        //因service无法注入，只能从bean工厂中拉取
        OrderMapper orderMapper = (OrderMapper) DelayUtils.getBean("OrderMapper");
        try {
            Integer integer = orderMapper.updateConfirm(orderid);
            if(integer >0 ){
                System.out.println("关闭订单");
            }else {
                System.out.println("关闭订单失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
