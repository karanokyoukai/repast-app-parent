package com.joewang.repast.controller;

import com.github.pagehelper.PageInfo;
import com.joewang.repast.base.BaseController;
import com.joewang.repast.base.ResultData;
import com.joewang.repast.model.OrderReturnApply;
import com.joewang.repast.page.PageInfos;
import com.joewang.repast.service.IOrderReturnApply;
import com.joewang.repast.utils.DecideToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 *      退款相关consumer层controller
 * @author: Joe Wang
 * @date: 2020-03-18
 */
@RestController
@Api(value = "退款相关", tags = "退款业务相关接口")
public class OrderReturnApplyController extends BaseController {
    @Autowired
    private IOrderReturnApply iOrderReturnApply;

    /**
     * @desc: 用户申请退款
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [orderReturnApply, token]
     * @return: com.joewang.repast.base.ResultData
     */
    @PostMapping("/insertOrderReturnApply")
    @ApiOperation(value = "退款申请", notes = "用户发起退款申请操作")
    public ResultData insertOrderReturnApply(OrderReturnApply orderReturnApply, @RequestParam("token") String token){
        if (DecideToken.decideToken(token)){
            Boolean ifSuccess = iOrderReturnApply.insertOrderReturnApply(orderReturnApply);
            if (ifSuccess){
                return operationSuccess("申请成功");
            }
            return operationFailed("申请失败");
        }
        return operationFailed("请重新登录");
    }

    /**
     * @desc: 根据订单id查询退款申请详情
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [orderId]
     * @return: com.joewang.repast.model.OrderReturnApply
     */
    @PostMapping("/selectMyOrderRetuenApplyByOrderId")
    @ApiOperation(value = "退款申请详情查询", notes = "根据订单id查询用户退款申请详情")
    public ResultData selectMyOrderRetuenApplyByOrderId(Long orderId, @RequestParam("token") String token){
        OrderReturnApply orderReturnApply = iOrderReturnApply.selectMyOrderRetuenApplyByOrderId(orderId);
        if (DecideToken.decideToken(token)){
            return operationSuccess(orderReturnApply);
        }
        return operationFailed("请重新登录");
    }

    /**
     * @desc: 根据店铺id查询退款申请, 分页查询
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [pageInfos]
     * @return: com.github.pagehelper.PageInfo<com.joewang.repast.model.OrderReturnApply>
     */
    @PostMapping("/selectOrderReturnByShopId")
    @ApiOperation(value = "店铺查询退款申请", notes = "根据店铺id查询收到的退款申请")
    public ResultData selectOrderReturnByShopId(PageInfos<Long> pageInfos, @RequestParam("token") String token){
        PageInfo<OrderReturnApply> pageInfo =  iOrderReturnApply.selectOrderReturnByShopId(pageInfos);
        if (DecideToken.decideToken(token)){
            return operationSuccess(pageInfo);
        }
        return operationFailed("请重新登录");
    }

    /**
     * @desc: 确认通过申请，开始退货流程
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [id]
     * @return: java.lang.Boolean
     */
    @PostMapping("/agreeOrderReturn")
    @ApiOperation(value = "通过退款申请", notes = "通过用户发来的退款申请")
    public ResultData agreeOrderReturn(OrderReturnApply orderReturnApply, @RequestParam("token") String token){
        if (DecideToken.decideToken(token)){
            Boolean ifSuccess = iOrderReturnApply.agreeOrderReturn(orderReturnApply);
            if (ifSuccess){
                return operationSuccess();
            }
            return operationFailed();
        }
        return operationFailed("请重新登录");
    }

    /**
     * @desc: 同意退款申请
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [id]
     * @return: java.lang.Boolean
     */
    @PostMapping("/confirmOrderReturnById")
    @ApiOperation(value = "同意退款申请", notes = "同意用户退款申请")
    public ResultData confirmOrderReturnById(OrderReturnApply orderReturnApply, @RequestParam("token") String token){
        if (DecideToken.decideToken(token)){
            Boolean ifSuccess = iOrderReturnApply.confirmOrderReturnById(orderReturnApply);
            if (ifSuccess){
                return operationSuccess();
            }
            return operationFailed();
        }
        return operationFailed("请重新登录");
    }

    /**
     * @desc: 拒绝用户退款申请
     * @author: Joe Wang
     * @date: 2020/3/18
     * @param: [id]
     * @return: java.lang.Boolean
     */
    @PostMapping("/refuseOrderReturnById")
    @ApiOperation(value = "拒绝退款申请", notes = "拒绝用户退款申请")
    public ResultData refuseOrderReturnById(OrderReturnApply orderReturnApply, @RequestParam("token") String token){
        if (DecideToken.decideToken(token)){
            Boolean ifSuccess = iOrderReturnApply.refuseOrderReturnById(orderReturnApply);
            if (ifSuccess){
                return operationSuccess();
            }
            return operationFailed();
        }
        return operationFailed("请重新登录");
    }
}
