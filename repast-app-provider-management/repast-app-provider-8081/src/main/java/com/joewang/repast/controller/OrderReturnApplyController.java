package com.joewang.repast.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.joewang.repast.model.OrderReturnApply;
import com.joewang.repast.page.PageInfos;
import com.joewang.repast.service.OrderReturnApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 *      退款相关provider层controller
 * @author: Joe Wang
 * @date: 2020-03-18
 */
@RestController
public class OrderReturnApplyController {
    @Autowired
    private OrderReturnApplyService orderReturnApplyService;

    /**
     * @desc: 用户申请退款方法
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [orderReturnApply]
     * @return: boolean
     */
    @PostMapping("/insertOrderReturnApply")
    public boolean insertOrderReturnApply(@RequestBody OrderReturnApply orderReturnApply){
        return orderReturnApplyService.insertOrderReturnApply(orderReturnApply);
    }

    /**
     * @desc: 根据订单id查询退款申请详情
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [orderId]
     * @return: com.joewang.repast.model.OrderReturnApply
     */
    @PostMapping("/selectMyOrderRetuenApplyByOrderId")
    public OrderReturnApply selectMyOrderRetuenApplyByOrderId(Long orderId){
        return orderReturnApplyService.selectMyOrderRetuenApplyByOrderId(orderId);
    }

    /**
     * @desc: 根据店铺id查询退款申请, 分页查询
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [pageInfos]
     * @return: com.github.pagehelper.PageInfo<com.joewang.repast.model.OrderReturnApply>
     */
    @PostMapping("/selectOrderReturnByShopId")
    public PageInfo<OrderReturnApply> selectOrderReturnByShopId(PageInfos<Long> pageInfos){
        return orderReturnApplyService.selectOrderReturnByShopId(pageInfos);
    }

    /**
     * @desc: 确认通过申请，开始退货流程
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [id]
     * @return: java.lang.Boolean
     */
    @PostMapping("/agreeOrderReturn")
    public Boolean agreeOrderReturn(OrderReturnApply orderReturnApply){
        return orderReturnApplyService.agreeOrderReturn(orderReturnApply);
    }

    /**
     * @desc: 同意退款申请
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [id]
     * @return: java.lang.Boolean
     */
    @PostMapping("/confirmOrderReturnById")
    public Boolean confirmOrderReturnById(OrderReturnApply orderReturnApply){
        return orderReturnApplyService.confirmOrderReturnById(orderReturnApply);
    }

    /**
     * @desc: 拒绝用户退款申请
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [id]
     * @return: java.lang.Boolean
     */
    @PostMapping("/refuseOrderReturnById")
    public Boolean refuseOrderReturnById(OrderReturnApply orderReturnApply){
        return orderReturnApplyService.refuseOrderReturnById(orderReturnApply);
    }
}
