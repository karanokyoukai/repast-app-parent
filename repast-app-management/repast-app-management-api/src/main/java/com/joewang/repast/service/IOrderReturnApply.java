package com.joewang.repast.service;

import com.github.pagehelper.PageInfo;
import com.joewang.repast.model.OrderReturnApply;
import com.joewang.repast.page.PageInfos;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 *      退款相关接口
 * @author: Joe Wang
 * @date: 2020-03-18
 */
@FeignClient(value = "memberinfo-interface",contextId = "OrderReturnApplyInterface")
public interface IOrderReturnApply {

    /**
     * @desc: 用户申请退款
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [orderReturnApply, token]
     * @return: boolean
     */
    @PostMapping("/insertOrderReturnApply")
    boolean insertOrderReturnApply(@RequestBody OrderReturnApply orderReturnApply);

    /**
     * @desc: 根据订单id查询退款申请详情
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [orderId]
     * @return: com.joewang.repast.model.OrderReturnApply
     */
    @PostMapping("/selectMyOrderRetuenApplyByOrderId")
    OrderReturnApply selectMyOrderRetuenApplyByOrderId(@RequestBody Long orderId);

    /**
     * @desc: 根据店铺id查询退款申请, 分页查询
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [pageInfos]
     * @return: com.github.pagehelper.PageInfo<com.joewang.repast.model.OrderReturnApply>
     */
    @PostMapping("/selectOrderReturnByShopId")
    PageInfo<OrderReturnApply> selectOrderReturnByShopId(@RequestBody PageInfos<Long> pageInfos);

    /**
     * @desc: 确认通过申请，开始退货流程
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [id]
     * @return: java.lang.Boolean
     */
    @PostMapping("/agreeOrderReturn")
    Boolean agreeOrderReturn(@RequestBody OrderReturnApply orderReturnApply);

    /**
     * @desc: 同意退款申请
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [id]
     * @return: java.lang.Boolean
     */
    @PostMapping("/confirmOrderReturnById")
    Boolean confirmOrderReturnById(@RequestBody OrderReturnApply orderReturnApply);

    /**
     * @desc: 拒绝用户退款申请
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [id]
     * @return: java.lang.Boolean
     */
    @PostMapping("/refuseOrderReturnById")
    Boolean refuseOrderReturnById(OrderReturnApply orderReturnApply);
}
