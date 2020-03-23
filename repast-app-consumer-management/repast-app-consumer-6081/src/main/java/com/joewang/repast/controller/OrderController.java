package com.joewang.repast.controller;

import com.joewang.repast.annotation.LoginLogAnnotation;
import com.joewang.repast.annotation.OrderLogAnnotation;
import com.joewang.repast.base.BaseController;
import com.joewang.repast.base.ResultData;
import com.joewang.repast.model.Order;
import com.joewang.repast.model.OrderItem;
import com.joewang.repast.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @date: create in 2020/3/18 14:39
 * @since JDK 1.8
 */
@RestController
@Api(value = "订单管理",tags = "订单接口")
public class OrderController extends BaseController {

    @Autowired
    private IOrderService orderService;

    /**
     * @Description:
     *      查询订单信息
     * @author:
     * @date: 2020/3/17 19:20
     * @param: [order, token]
     * @return: java.util.List<java.util.Map>
     */
    @PostMapping("/selectOrderByMemberId")
    @ApiOperation(value = "订单查询", notes = "根据不同的状态显示不同的订单")
    public List<Map> selectOrderByMemberId(@RequestParam("memberId") Long memberId , @RequestParam("token") String token){
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
    @ApiOperation(value = "订单查询", notes = "根据订单信息查询订单")
    public ResultData selectOrder(@RequestBody Order order , @RequestParam("token") String token){
        Order order1 = orderService.selectOrder(order, token);
        if (order1 != null){
            return operationSuccess(order1);
        }
        return operationFailed();
    }

    /**
     * @Description:
     *      新增订单
     * @author:
     * @date: 2020/3/17 19:08
     * @param: [order, token]
     * @return: java.lang.Boolean
     */
    @PostMapping("/insertOrder")
    @ApiOperation(value = "新增订单", notes = "新增订单")
    @OrderLogAnnotation (operationType = "新增操作", operationName = "普通用户登录")
    public ResultData insertOrder(@RequestBody Map<String,Object> map, @RequestParam("token") String token){

        Boolean aBoolean = orderService.insertOrder(map, token);
        if (aBoolean){
            return operationSuccess();
        }
        return operationFailed();
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
    @ApiOperation(value = "取消订单", notes = "取消订单")
    public ResultData cancelOrder(@RequestParam("orderId") Long orderId,@RequestParam("token")String token){
        Boolean aBoolean = orderService.cancelOrder(orderId, token);
        if (aBoolean){
            return operationSuccess();
        }
        return operationFailed();
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
    @ApiOperation(value = "删除订单", notes = "删除订单")
    @OrderLogAnnotation (operationType = "删除操作", operationName = "普通用户登录")
    public ResultData deleteOrder(@RequestParam("orderId") Long orderId,@RequestParam("token")String token){
        Boolean aBoolean = orderService.deleteOrder(orderId, token);
        if (aBoolean){
            return operationSuccess();
        }
        return operationSuccess();
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
    @ApiOperation(value = "确认收货", notes = "确认收货")
    public ResultData updateConfirm(@RequestParam("orderId") Long orderId,@RequestParam("token") String token){
        Boolean aBoolean = orderService.updateConfirm(orderId, token);
        if (aBoolean){
            return operationSuccess();
        }
        return operationFailed();
    }

}
