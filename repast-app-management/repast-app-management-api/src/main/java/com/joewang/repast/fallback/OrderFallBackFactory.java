package com.joewang.repast.fallback;

import com.joewang.repast.model.OperateHistory;
import com.joewang.repast.model.Order;
import com.joewang.repast.model.OrderItem;
import com.joewang.repast.service.IOrderService;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.client.loadbalancer.reactive.ReactiveLoadBalancer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author zxz
 * @ClassName: OrderFallBackFactory
 * @Description:
 * @date: create in 2020/3/18 14:34
 * @since JDK 1.8
 */
@Component
public class OrderFallBackFactory implements FallbackFactory<IOrderService> {
    @Override
    public IOrderService create(Throwable throwable) {
        IOrderService iOrderService = new IOrderService() {
            @Override
            public List<Map> selectOrderByMemberId(Long memberId, String token) {
                System.out.println("熔断查询所有订单");
                return null;
            }

            @Override
            public Order selectOrder(Order order, String token) {
                System.out.println("熔断根据订单信息查询订单");
                return null;
            }

            @Override
            public Boolean insertOrder(Map map, String token) {
                System.out.println("熔断新增订单");
                return null;
            }

            @Override
            public Boolean cancelOrder(Long orderId, String token) {
                System.out.println("熔断取消订单");
                return null;
            }

            @Override
            public Boolean addOrderLog(OperateHistory operateHistory) {
                System.out.println("熔断订单日志保存");
                return null;
            }

            @Override
            public Boolean deleteOrder(Long orderId, String token) {
                System.out.println("熔断删除订单");
                return null;
            }

            @Override
            public Boolean updateConfirm(Long orderId, String token) {
                System.out.println("熔断确认收货");
                return null;
            }
        };
        return null;
    }
}
