package com.joewang.repast.service;

import com.joewang.repast.model.Order;
import com.joewang.repast.utils.DelayCancelOrderTaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zxz
 * @ClassName: OrderPayTimeoutQueueRunner
 * @Description:
 *      把未完成订单加入到消息队列中
 * @date: create in 2020/3/18 15:27
 * @since JDK 1.8
 */
@Component
public class OrderPayTimeoutQueueRunner implements CommandLineRunner {

    @Autowired
    private  OrderService orderService;


    @Override
    public void run(String... strings) throws Exception {
        new Thread(){
            @Override
            public void run() {
                DelayCancelOrderTaskManager delayCancelOrderTaskManager = DelayCancelOrderTaskManager.getInstance();
                List<Order> OrderList = orderService.selectPayNotTimeoutOrderList();
                try{
                    for(Order order : OrderList){
                        PayTimeoutCancelOrderProcessor processor = new PayTimeoutCancelOrderProcessor(order.getId());
                        delayCancelOrderTaskManager.putTaskInTimeoutTime(processor, LocalDateTime.now().plusMinutes(15));
                    }
                }catch (NullPointerException e){
                    System.out.println("没有可加载的数据");
                }
            }
        }.start();

    }

}
