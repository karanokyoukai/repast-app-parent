package com.joewang.repast.controller;

import com.joewang.repast.model.OperateHistory;
import com.joewang.repast.model.Order;
import com.joewang.repast.model.OrderItem;
import com.joewang.repast.service.OperateHistoryService;
import com.joewang.repast.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author zxz
 * @ClassName: OrderController
 * @Description:
 * @date: create in 2020/3/18 13:55
 * @since JDK 1.8
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OperateHistoryService operateHistoryService;


    /**
     * @Description:
     *      查询订单信息
     * @author:
     * @date: 2020/3/17 19:20
     * @param: [order, token]
     * @return: java.util.List<java.util.Map>
     */
    @PostMapping("/selectOrderByMemberId")
    public List<Map> selectOrderByMemberId(@RequestParam("memberId") Long memberId ,@RequestParam("token") String token){
        return orderService.selectOrderByMemberId(memberId, token);
    }

    /**
     * @Description:
     *      根据订单信息查询订单
     * @author:
     * @date: 2020/3/17 19:20
     * @param: [order, token]
     * @return: com.joewang.repast.model.Order
     */
    @PostMapping("/selectOrder")
    public Order selectOrder(@RequestBody Order order ,@RequestParam("token") String token){
        return orderService.selectOrder(order, token);
    }

    /**
     * @Description:
     *      新增订单
     * @author:
     * @date: 2020/3/17 19:08
     * @param: [order, token]
     * @return: java.lang.Boolean
     */
    //@Scheduled(fixedDelay = 1800)
    @PostMapping("/insertOrder")
    public Boolean insertOrder(@RequestBody Map<String,Object> map,@RequestParam("token") String token){
        return orderService.insertOrder(map, token);
    }

    /**
     * @desc: 订单日志保存
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: [operateHistory]
     * @return: java.lang.Boolean
     */
    @PostMapping("/addOrderLog")
    Boolean addOrderLog(@RequestBody OperateHistory operateHistory){
        return operateHistoryService.addOrderLog(operateHistory);
    }

    /**
     * @Description:
     *      取消订单
     * @author:
     * @date: 2020/3/17 19:08
     * @param: [order, token]
     * @return: java.lang.Boolean
     */
    @PostMapping("/cancelOrder")
    public Boolean cancelOrder(@RequestParam("orderId") Long orderId,@RequestParam("token")String token){
        return orderService.cancelOrder(orderId, token);
    }

    /**
     * @Description:
     *      删除订单
     * @author:
     * @date: 2020/3/17 19:08
     * @param: [order, token]
     * @return: java.lang.Boolean
     */
    @PostMapping("/deleteOrder")
    public Boolean deleteOrder(@RequestParam("orderId") Long orderId,@RequestParam("token")String token){
        return orderService.deleteOrder(orderId, token);
    }

    /**
     * @Description:
     *      确认收货
     * @author:
     * @date: 2020/3/17 19:08
     * @param: [order, token]
     * @return: java.lang.Boolean
     */
    @PostMapping("/updateConfirm")
    public Boolean updateConfirm(@RequestParam("orderId") Long orderId,@RequestParam("token") String token){
        return orderService.updateConfirm(orderId, token);
    }



    /**
     * @Description:
     *      新增订单中的商品
     * @author:
     * @date: 2020/3/17 19:44
     * @param: [products]
     * @return: java.lang.Boolean
     */
    public Boolean insertOrderItem(@RequestBody List<OrderItem> products, @RequestParam("token") String token){
        return orderService.insertOrderItem(products,token);
    }


}
