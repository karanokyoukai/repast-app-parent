package com.joewang.repast.service;

import com.joewang.repast.base.BaseService;
import com.joewang.repast.mapper.OrderItemMapper;
import com.joewang.repast.mapper.OrderMapper;
import com.joewang.repast.model.Order;
import com.joewang.repast.model.OrderItem;
import com.joewang.repast.model.Product;
import com.joewang.repast.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author zxz
 * @ClassName: OrderService
 * @Description:
 * @date: create in 2020/3/17 19:01
 * @since JDK 1.8
 */
@Service
public class OrderService extends BaseService<Order> {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public Mapper<Order> getMapper() {
        return orderMapper;
    }

    /**
     * @Description:
     *      查询订单及信息
     * @author:
     * @date: 2020/3/17 19:20
     * @param: [order, token]
     * @return: java.util.List<java.util.Map>
     */
    public List<Map> selectOrderByMemberId(Long memberId ,String token){
        try {
            List<Map> list = orderMapper.selectOrderByMemberId(memberId);
            if (list != null && list.size()>0){
                return list;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description:
     *      根据订单信息查询订单
     * @author:
     * @date: 2020/3/17 19:20
     * @param: [order, token]
     * @return: com.joewang.repast.model.Order
     */
    public Order selectOrder(Order order ,String token){

        try {
            Order order1 = orderMapper.selectOne(order);
            if (order1 != null){
                return order1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description:
     *      新增订单
     * @author:
     * @date: 2020/3/17 19:08
     * @param: [order, token]
     * @return: java.lang.Boolean
     */
    public  Boolean insertOrder(Map map, String token){
        try {
            Order order1 = JSONUtil.toObject( JSONUtil.toJsonString(map.get("order")),Order.class);
            Integer result = orderMapper.insert(order1);
            if (result > 0){
                List<OrderItem> orderItemList = new ArrayList<>();
                String arr = JSONUtil.toJsonString(map.get("orderItems"));
                System.out.println(arr);
                List<Map> orderItems = JSONUtil.toObject( arr,List.class);
                for (Map map3: orderItems){
                    OrderItem orderItem = Map2BeanUtil.map2Bean(map3,OrderItem.class);
                    System.out.println(orderItem);
                    orderItemList.add(orderItem);
                }
                System.out.println(orderItemList);
                Boolean aBoolean = this.insertOrderItem(orderItemList, token);
                if(aBoolean){
                    DelayCancelOrderTaskManager delayCancelOrderTaskManager = DelayCancelOrderTaskManager.getInstance();
                    PayTimeoutCancelOrderProcessor processor = new PayTimeoutCancelOrderProcessor(order1.getId());
                    delayCancelOrderTaskManager.putTaskInTimeoutTime(processor, LocalDateTime.now().plusMinutes(15));
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }



    /**
     * @Description:
     *      取消订单
     * @author:
     * @date: 2020/3/17 19:08
     * @param: [order, token]
     * @return: java.lang.Boolean
     */
    public Boolean cancelOrder(Long orderId,String token){

        Integer integer = orderMapper.cancelOrder(orderId);
        if(integer>0){
            //取消预约单，则删除延时队列中的超时等待信号
            Order order = orderMapper.selectOrderById(orderId);
            DelayCancelOrderTaskManager delayCancelOrderTaskManager = DelayCancelOrderTaskManager.getInstance();
            PayTimeoutCancelOrderProcessor processor = new PayTimeoutCancelOrderProcessor(order.getId());
            Duration duration = Duration.between(LocalDateTime.now(), LocalDateTime.now().plusMinutes(15));
            long timeout = TimeUnit.NANOSECONDS.convert(duration.toNanos(), TimeUnit.NANOSECONDS);
            DelayCancelOrderTask<?> delayCancelOrderTask = new DelayCancelOrderTask<>(timeout, processor);
            delayCancelOrderTaskManager.removeTask(delayCancelOrderTask);
            return true;
        }else {
            return false;
        }

    }

    /**
     * @Description:
     *     查询未完成订单
     * @author:
     * @date: 2020/3/17 19:08
     * @param: [order, token]
     * @return: java.lang.Boolean
     */
    public List<Order> selectPayNotTimeoutOrderList(){

        List<Order> result = orderMapper.selectPayNotTimeoutOrderList();
        if (result != null){
            return result;
        }
        return null;
    }


    /**
     * @Description:
     *      删除订单
     * @author:
     * @date: 2020/3/17 19:08
     * @param: [order, token]
     * @return: java.lang.Boolean
     */
    public Boolean deleteOrder(Long orderId,String token){

        Integer result = orderMapper.deleteOrder(orderId);
        if (result > 0){
            return true;
        }
        return false;
    }

    /**
     * @Description:
     *      确认收货
     * @author:
     * @date: 2020/3/17 19:08
     * @param: [order, token]
     * @return: java.lang.Boolean
     */
    public Boolean updateConfirm(Long orderId,String token){

        Integer result = orderMapper.updateConfirm(orderId);
        if (result > 0){
            return true;
        }
        return false;
    }



    /**
     * @Description:
     *      新增订单中的商品
     * @author:
     * @date: 2020/3/17 19:44
     * @param: [products]
     * @return: java.lang.Boolean
     */
    public Boolean insertOrderItem(List<OrderItem> products,String token){

        try {
            for (OrderItem orderItem : products){
                orderItemMapper.insertSelective(orderItem);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }



}
