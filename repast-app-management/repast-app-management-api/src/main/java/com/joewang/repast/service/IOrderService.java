package com.joewang.repast.service;

import com.joewang.repast.model.LoginLog;
import com.joewang.repast.model.OperateHistory;
import com.joewang.repast.model.Order;
import com.joewang.repast.model.OrderItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author zxz
 * @ClassName: IOrderService
 * @Description:
 * @date: create in 2020/3/18 14:30
 * @since JDK 1.8
 */
@FeignClient(value = "memberinfo-interface", contextId = "order")
public interface IOrderService {


    /**
     * @Description:
     *      查询订单信息
     * @author:
     * @date: 2020/3/17 19:20
     * @param: [order, token]
     * @return: java.util.List<java.util.Map>
     */
    @PostMapping("/selectOrderByMemberId")
    List<Map> selectOrderByMemberId(@RequestParam("memberId") Long memberId , @RequestParam("token") String token);

    /**
     * @Description:
     *      根据订单信息查询订单
     * @author:
     * @date: 2020/3/17 19:20
     * @param: [order, token]
     * @return: com.joewang.repast.model.Order
     */
    @PostMapping("/selectOrder")
    Order selectOrder(@RequestBody Order order , @RequestParam("token") String token);

    /**
     * @Description:
     *      新增订单
     * @author:
     * @date: 2020/3/17 19:08
     * @param: [order, token]
     * @return: java.lang.Boolean
     */
    @PostMapping("/insertOrder")
    Boolean insertOrder(@RequestBody Map<String,Object> map, @RequestParam("token") String token);

    /**
     * @Description:
     *      取消订单
     * @author:
     * @date: 2020/3/17 19:08
     * @param: [order, token]
     * @return: java.lang.Boolean
     */
    @PostMapping("/cancelOrder")
    Boolean cancelOrder(@RequestParam("orderId") Long orderId,@RequestParam("token")String token);

    /**
     * @desc: 订单日志保存
     * @author: Joe Wang
     * @date: 2020/3/11
     * @param: [operateHistory]
     * @return: java.lang.Boolean
     */
    @PostMapping("/addOrderLog")
    Boolean addOrderLog(@RequestBody OperateHistory operateHistory);

    /**
     * @Description:
     *      删除订单
     * @author:
     * @date: 2020/3/17 19:08
     * @param: [order, token]
     * @return: java.lang.Boolean
     */
    @PostMapping("/deleteOrder")
    Boolean deleteOrder(@RequestParam("orderId") Long orderId,@RequestParam("token")String token);

    /**
     * @Description:
     *      确认收货
     * @author:
     * @date: 2020/3/17 19:08
     * @param: [order, token]
     * @return: java.lang.Boolean
     */
    @PostMapping("/updateConfirm")
    Boolean updateConfirm(@RequestParam("orderId") Long orderId,@RequestParam("token") String token);

}
