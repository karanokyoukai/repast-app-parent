package com.joewang.repast.mapper;

import com.joewang.repast.model.Order;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface OrderMapper extends Mapper<Order> {

    /**
     * @Description:
     *      取消订单
     * @author:
     * @date: 2020/3/17 19:16
     * @param: [memberId]
     * @return: java.lang.Integer
     */
    Integer cancelOrder(Long orderId);

    /**
     * @Description:
     *      删除订单
     * @author:
     * @date: 2020/3/17 19:16
     * @param: [memberId]
     * @return: java.lang.Integer
     */
    Integer deleteOrder(Long orderId);

    /**
     * @Description:
     *      确认收货
     * @author:
     * @date: 2020/3/17 19:16
     * @param: [memberId]
     * @return: java.lang.Integer
     */
    Integer updateConfirm(Long orderId);


    /**
     * @Description:
     *      通过Id查询订单
     * @author:
     * @date: 2020/3/17 19:16
     * @param: [memberId]
     * @return: com.joewang.repast.model.Order
     */
    Order selectOrderById(Long orderId);

    /**
     * @Description:
     *      查询订单和订单中商品信息
     * @author:
     * @date: 2020/3/18 11:34
     * @param: [memberId]
     * @return: java.util.List<java.util.Map>
     */
    List<Map> selectOrderByMemberId(Long memberId);

    /**
     * @Description:
     *      查询未付款的订单
     * @author:
     * @date: 2020/3/18 15:36
     * @param: []
     * @return: java.util.List<com.joewang.repast.model.Order>
     */
    List<Order> selectPayNotTimeoutOrderList();

    /**
     * @Description:
     *      通过订单编号修改为无效订单
     * @author:
     * @date: 2020/3/18 16:02
     * @param: [ordersn]
     * @return: java.lang.Integer
     */
    Integer updateOrderStatus(@Param("ordersn") String ordersn);

}